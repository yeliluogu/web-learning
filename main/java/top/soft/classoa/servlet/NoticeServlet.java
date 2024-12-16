package top.soft.classoa.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.soft.classoa.entity.Notice;
import top.soft.classoa.service.NoticeService;
import top.soft.classoa.utils.ResponseUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author lenovo
 * @description: TODO
 * @date 2024/12/15 01:18
 */
@WebServlet("/api/notice/list")
public class NoticeServlet extends HttpServlet {
    private final NoticeService noticeService = new NoticeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = req.getParameter("eid");
        ResponseUtils result;
        try{
            List<Notice> noticeList = noticeService.getNoticeList(Long.parseLong(employeeId));
            result = new ResponseUtils().put("list", noticeList);
        }catch (Exception e){
            e.printStackTrace();
            result = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(result.toJsonString());
    }
}
