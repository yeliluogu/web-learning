package top.soft.classoa.service;

import top.soft.classoa.entity.Employee;
import top.soft.classoa.entity.LeaveForm;
import top.soft.classoa.entity.Notice;
import top.soft.classoa.entity.ProcessFlow;
import top.soft.classoa.mapper.EmployeeMapper;
import top.soft.classoa.mapper.LeaveFormMapper;
import top.soft.classoa.mapper.NoticeMapper;
import top.soft.classoa.mapper.ProcessFlowMapper;
import top.soft.classoa.service.exception.LeaveFormException;
import top.soft.classoa.utils.DateUtils;
import top.soft.classoa.utils.MybatisUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LeaveFormService {
    private static final int AUTO_APPROVE_LEVEL = 8;
    private final EmployeeService employeeService = new EmployeeService();

    public void audit(Long formId,Long operatorId,String result,String reason){
        MybatisUtils.executeQuery(sqlSession -> {
            ProcessFlowMapper processFlowMapper = sqlSession.getMapper(ProcessFlowMapper.class);
            List<ProcessFlow> flowList = processFlowMapper.selectByFormId(formId);
            if (flowList.size() == 0){
                throw new LeaveFormException("无效的审批流程");
            }
            List<ProcessFlow> processFlowList = flowList.stream().filter(p -> Objects.equals(p.getOperatorId(),operatorId)&&"process".equals(p.getState())).toList();
            ProcessFlow process;
            if (processFlowList.size() == 0){
                throw new LeaveFormException("未找到带处理任务节点");
            }else {
                process = processFlowList.get(0);
                process.setState("complete");
                process.setResult(result);
                process.setReason(reason);
                process.setAuditTime(new Date());
                processFlowMapper.update(process);
            }
            LeaveFormMapper leaveFormMapper = sqlSession.getMapper(LeaveFormMapper.class);
            LeaveForm form = leaveFormMapper.selectById(formId);
            Employee employee = employeeService.selectById(form.getEmployeeId());
            Employee operator = employeeService.selectById(operatorId);
            NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH时");
            if (process.getIsLast() == 1){
                form.setState(result);
                leaveFormMapper.update(form);
                String strResult = null;
                if ("approve".equals(result)){
                    strResult = "批准";
                }else if ("reject".equals(result)){
                    strResult = "驳回";
                }

                String notice1 =String.format("您的请假申请[%s-%s]%s%s已%s,审批意见:%s,审批流程已结束",
                        sdf.format(form.getStartTime()),
                        sdf.format(form.getEndTime()),
                        operator.getTitle(),
                        operator.getName(),
                        strResult,
                        reason);
                noticeMapper.insert(Notice.builder().receiverId(form.getEmployeeId()).content(notice1).createTime(new Date()).build());

                String notice2 = String.format("%s-%s提起请假申请[%s-%s]您已%s,审批意见:%s,审批流程已结束",
                         employee.getTitle(),
                         employee.getName(),
                         sdf.format(form.getStartTime()),
                         sdf.format(form.getEndTime()),
                         strResult,reason);
                 noticeMapper.insert(Notice.builder().receiverId(operator.getEmployeeId()).content(notice2).createTime(new Date()).build());
            }else {
                List<ProcessFlow> readyList = flowList.stream().filter(p -> "ready".equals(p.getState())).toList();
                if ("approved".equals(result)){
                    ProcessFlow readyProcess = readyList.get(0);
                    readyProcess.setState("process");
                    processFlowMapper.update(readyProcess);
                    String notice1 = String.format("您的请假申请[%s-%s]%s%s已通过,审批意见:%s,请继续等待上级审批",
                            sdf.format(form.getStartTime()),
                            sdf.format(form.getEndTime()),
                            operator.getTitle(),
                            operator.getName(),
                            reason);
                    noticeMapper.insert(
                            Notice.builder()
                                    .receiverId(form.getEmployeeId())
                                    .content(notice1)
                                    .createTime(new Date())
                                     .build());

                    String notice2 = String.format("%s-%s提起请假申请[%s-%s]您已批准,审批意见:%s,申请转至上级领导继续审批",
                            employee.getTitle(),
                            employee.getName(),
                            sdf.format(form.getStartTime()),
                            sdf.format(form.getEndTime()),
                            reason);
                    noticeMapper.insert(
                            Notice.builder()
                                    .receiverId(operator.getEmployeeId())
                                    .content(notice2)
                                    .createTime(new Date())
                                    .build());
                    String notice3 = String.format("%s-%s提起请假申请[%s-%s],请尽快审批",
                            employee.getTitle(),
                            employee.getName(),
                            sdf.format(form.getStartTime()),
                            sdf.format(form.getEndTime()));
                    noticeMapper.insert(Notice.builder().receiverId(readyProcess.getOperatorId()).content(notice3).createTime(new Date()).build());


                } else if ("rejected".equals(result)) {
                    for (ProcessFlow p : readyList) {
                        p.setState("cancel");
                        processFlowMapper.update(p);
                    }
                    form.setState("refused");
                    leaveFormMapper.update(form);

                    String notice1 = String.format("您的请假申请[%s-%s]%s%s已被驳回,审批意见:%s,审批流程已结束",
                            sdf.format(form.getStartTime()),
                            sdf.format(form.getEndTime()),
                            operator.getTitle(),
                            operator.getName(),
                            reason);
                    noticeMapper.insert(Notice.builder().receiverId(operator.getEmployeeId()).content(notice1).createTime(new Date()).build());

                    String notice2 = String.format("%s-%s 提起请假申请[%s-%s]您已驳回,审批意见:%s,审批流程已结束",
                            employee.getTitle(),
                            employee.getName(),
                            sdf.format(form.getStartTime()),
                            sdf.format(form.getEndTime()),reason);
                    noticeMapper.insert(Notice.builder().receiverId(operator.getEmployeeId()).content(notice2).createTime(new Date()).build());

                }
            }
            return null;
        });
    }

    public LeaveForm createLeaveForm(LeaveForm leaveForm) {
        return (LeaveForm) MybatisUtils.executeUpdate(sqlSession -> {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = employeeMapper.selectById(leaveForm.getEmployeeId());
            if (employee == null) {
                throw new LeaveFormException("Employee not found");
            }

            if (employee.getLevel() == AUTO_APPROVE_LEVEL) {
                leaveForm.setState("approved");
            } else {
                leaveForm.setState("processing");
            }

            LeaveFormMapper leaveFormMapper = sqlSession.getMapper(LeaveFormMapper.class);
            leaveFormMapper.insert(leaveForm);
            LeaveForm latestForm = leaveFormMapper.selectLatestForm();
            System.out.println(latestForm);

            ProcessFlowMapper processFlowMapper = sqlSession.getMapper(ProcessFlowMapper.class);
            ProcessFlow flow1 = ProcessFlow.builder()
                    .formId(latestForm.getFormId())
                    .operatorId(latestForm.getEmployeeId())
                    .action("apply")
                    .createTime(new Date())
                    .orderNo(1)
                    .state("complete")
                    .isLast(0)
                    .build();
            processFlowMapper.insert(flow1);

            int level = employee.getLevel();
            switch (level) {
                case 1, 2, 3, 4, 5, 6 -> {
                    handleNormalApproval(latestForm, processFlowMapper, employee);
                }
                case 7 -> {
                    handleBossApproval(latestForm, processFlowMapper, employee);
                }
                case AUTO_APPROVE_LEVEL -> {
                    handleAutoApproval(latestForm, processFlowMapper, employee);
                }
                default -> throw new LeaveFormException("Invalid employee level");
            }
            return latestForm;
        });
    }

    private void handleNormalApproval(LeaveForm leaveForm, ProcessFlowMapper processFlowMapper, Employee employee) {
        Employee leader = employeeService.selectLeader(employee.getEmployeeId());
        if (leader == null) {
            throw new LeaveFormException("Leader not found");
        }

        ProcessFlow flow2 = new ProcessFlow();
        flow2.setFormId(leaveForm.getFormId());
        flow2.setOperatorId(leader.getEmployeeId());
        flow2.setAction("audit");
        flow2.setCreateTime(new Date());
        flow2.setOrderNo(2);
        flow2.setState("process");

        long hours = DateUtils.getDiffHours(leaveForm.getCreateTime(), leaveForm.getEndTime());

        if (hours >= 72) {
            flow2.setIsLast(0);
            processFlowMapper.insert(flow2);

            Employee boss = employeeService.selectLeader(employee.getEmployeeId());
            if (boss == null) {
                throw new LeaveFormException("Boss not found");
            }

            ProcessFlow flow3 = new ProcessFlow();
            flow3.setFormId(leaveForm.getFormId());
            flow3.setOperatorId(boss.getEmployeeId());
            flow3.setAction("audit");
            flow3.setCreateTime(new Date());
            flow3.setState("ready");
            flow3.setOrderNo(3);
            flow3.setIsLast(1);
            processFlowMapper.insert(flow3);
        } else {
            flow2.setIsLast(1);
            processFlowMapper.insert(flow2);
        }
    }


    private void handleBossApproval(LeaveForm leaveForm, ProcessFlowMapper processFlowMapper, Employee employee) {
        Employee boss = employeeService.selectLeader(employee.getEmployeeId());
        if (boss == null) {
            throw new LeaveFormException("Boss not found");
        }

        ProcessFlow flow2 = new ProcessFlow();
        flow2.setFormId(leaveForm.getFormId());
        flow2.setOperatorId(boss.getEmployeeId());
        flow2.setAction("audit");
        flow2.setCreateTime(new Date());
        flow2.setState("process");
        flow2.setOrderNo(2);
        flow2.setIsLast(1);
        processFlowMapper.insert(flow2);
    }

    private void handleAutoApproval(LeaveForm leaveForm, ProcessFlowMapper processFlowMapper, Employee employee) {
        ProcessFlow flow2 = new ProcessFlow();
        flow2.setFormId(leaveForm.getFormId());
        flow2.setOperatorId(employee.getEmployeeId());
        flow2.setAction("audit");
        flow2.setResult("approved");
        flow2.setReason("自动通过");
        flow2.setCreateTime(new Date());
        flow2.setAuditTime(new Date());
        flow2.setState("completed");
        flow2.setOrderNo(2);
        flow2.setIsLast(1);
        processFlowMapper.insert(flow2);
    }

    public List<Map<String, Object>> getLeaveFormList(String state, Long operatorId) {
        return (List<Map<String, Object>>) MybatisUtils.executeQuery(sqlSession -> {
            LeaveFormMapper leaveFormMapper = sqlSession.getMapper(LeaveFormMapper.class);
            List<Map<String, Object>> map = leaveFormMapper.selectByParams(state, operatorId);
            return map;
        });
    }
}