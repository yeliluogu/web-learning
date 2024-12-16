package top.soft.classoa.service;

import org.junit.jupiter.api.Test;
import top.soft.classoa.entity.LeaveForm;
import top.soft.classoa.mapper.LeaveFormMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lenovo
 * @description: TODO
 * @date 2024/12/7 17:04
 */
 class LeaveFormServiceTest {
    LeaveFormService levelFormService = new LeaveFormService();
    @Test
    void createLeaveForm1() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        LeaveForm form = new LeaveForm();
        form.setEmployeeId(8L);
        form.setStartTime(sdf.parse("2024120409"));
        form.setEndTime(sdf.parse("2024121409"));
        form.setFormType(1);
        form.setReason("市场部员工请假单（72小时以上）");
        form.setCreateTime(new Date());
        LeaveForm savedForm = levelFormService.createLeaveForm(form);
        System.out.println(savedForm);
    }
    @Test
    void createLeaveForm2() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        LeaveForm form = new LeaveForm();
        form.setEmployeeId(8L);
        form.setStartTime(sdf.parse("2024120409"));
        form.setEndTime(sdf.parse("2024121509"));
        form.setFormType(1);
        form.setReason("市场部员工请假单（72小时以上）");
        form.setCreateTime(new Date());
        LeaveForm savedForm = levelFormService.createLeaveForm(form);
        System.out.println(savedForm);
    }
    @Test
    void createLeaveForm3() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        LeaveForm form = new LeaveForm();
        form.setEmployeeId(2L);
        form.setStartTime(sdf.parse("2024120409"));
        form.setEndTime(sdf.parse("2024121509"));
        form.setFormType(1);
        form.setReason("研发部门经理请假单");
        form.setCreateTime(new Date());
        LeaveForm savedForm = levelFormService.createLeaveForm(form);
        System.out.println(savedForm);
    }
    @Test
    void createLeaveForm4() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        LeaveForm form = new LeaveForm();
        form.setEmployeeId(1L);
        form.setStartTime(sdf.parse("2024120409"));
        form.setEndTime(sdf.parse("2024121509"));
        form.setFormType(1);
        form.setReason("总经了请假单");
        form.setCreateTime(new Date());
        LeaveForm savedForm = levelFormService.createLeaveForm(form);
        System.out.println(savedForm);
    }
}

