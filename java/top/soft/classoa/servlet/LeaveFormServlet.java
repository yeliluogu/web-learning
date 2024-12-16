package top.soft.classoa.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.soft.classoa.entity.LeaveForm;
import top.soft.classoa.service.LeaveFormService;
import top.soft.classoa.utils.ResponseUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lenovo
 * @description: TODO
 * @date 2024/12/7 17:57
 */
@WebServlet("/api/leave/*")
public class LeaveFormServlet extends HttpServlet {
    private final LeaveFormService leaveFormService = new LeaveFormService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        String uri = req.getRequestURI();
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        switch (methodName) {
            case "create" -> this.create(req, resp);
            case "list" -> this.list(req, resp);
            case "audit" -> this.audit(req, resp);
            default -> System.out.println("请求错误");
        }
    }

    private void audit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String formId = req.getParameter("formId");
        String result = req.getParameter("result");
        String reason = req.getParameter("reason");
        String eid = req.getParameter("eid");
        ResponseUtils response;
        try {
            leaveFormService.audit(Long.parseLong(formId), Long.parseLong(eid), result, reason);
            response = new ResponseUtils();
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        resp.getWriter().write(response.toJsonString());
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = req.getParameter("eid");
        ResponseUtils result;
        try {
            List<Map<String, Object>> formList = leaveFormService.getLeaveFormList("process", Long.parseLong(employeeId));
            result = new ResponseUtils().put("list", formList);
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        resp.getWriter().println(result.toJsonString());
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = req.getParameter("eid");
        String formType = req.getParameter("formType");
        String startTime = req.getParameter("startTime");
        String endTime = req.getParameter("endTime");
        String reason = req.getParameter("reason");

        ResponseUtils result;
        try {
            // 校验输入参数
            if (isInvalidNumber(employeeId)) {
                throw new IllegalArgumentException("Invalid employeeId");
            }
            if (isInvalidNumber(formType)) {
                throw new IllegalArgumentException("Invalid formType");
            }
            if (isInvalidNumber(startTime)) {
                throw new IllegalArgumentException("Invalid startTime");
            }
            if (isInvalidNumber(endTime)) {
                throw new IllegalArgumentException("Invalid endTime");
            }

            // 进一步校验 startTime 是否为有效的时间戳
            long startTimeLong = Long.parseLong(startTime);
            if (startTimeLong < 0) {
                throw new IllegalArgumentException("Invalid startTime: must be a positive number");
            }

            // 进一步校验 startTime 是否在合理的时间范围内
            long currentTimeMillis = System.currentTimeMillis();
            if (startTimeLong > currentTimeMillis + 365L * 24 * 60 * 60 * 1000) { // 未来一年内的时间戳
                throw new IllegalArgumentException("Invalid startTime: too far in the future");
            }

            // 进一步校验 endTime 是否为有效的时间戳
            long endTimeLong = Long.parseLong(endTime);
            if (endTimeLong < 0) {
                throw new IllegalArgumentException("Invalid endTime: must be a positive number");
            }

            // 进一步校验 endTime 是否在合理的时间范围内
            if (endTimeLong > currentTimeMillis + 365L * 24 * 60 * 60 * 1000) { // 未来一年内的时间戳
                throw new IllegalArgumentException("Invalid endTime: too far in the future");
            }

            // 进一步校验 startTime 是否早于 endTime
            if (startTimeLong >= endTimeLong) {
                throw new IllegalArgumentException("Invalid startTime and endTime: startTime must be before endTime");
            }

            LeaveForm form = LeaveForm.builder()
                    .employeeId(Long.parseLong(employeeId))
                    .formType(Integer.parseInt(formType))
                    .startTime(new Date(startTimeLong))
                    .endTime(new Date(endTimeLong))
                    .reason(reason)
                    .createTime(new Date())
                    .build();

            LeaveForm leaveForm = leaveFormService.createLeaveForm(form);
            result = new ResponseUtils();
            result.put("leaveForm", leaveForm);
        } catch (NumberFormatException e) {
            result = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        } catch (IllegalArgumentException e) {
            result = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        resp.getWriter().println(result.toJsonString());
    }

    // 辅助方法，检查字符串是否为有效的数字
    private boolean isInvalidNumber(String value) {
        return value == null || value.trim().isEmpty() || !value.matches("\\d+");
    }

}
