package top.soft.bookonline.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import top.soft.bookonline.service.UserServices;
import top.soft.bookonline.service.impl.UserServiceimpl;


import java.io.IOException;

/**
 * @author lenovo
 * @description: TODO
 * @date 2024/10/26 15:41
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserServices userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = new UserServiceimpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        User user=userService.signIn(account,password);
        if(user!=null){
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            resp.sendRedirect("/index");
        }else {
            resp.setContentType("text/html;charset=utf-8");
            resp.setCharacterEncoding("utf-8");
            resp.getWriter().write("<script>alert('账号或密码错误‘）；location.href='/';</script>");

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}