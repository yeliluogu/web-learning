package top.soft.bookonline.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.soft.bookonline.service.UserServices;
import top.soft.bookonline.service.impl.UserServiceimpl;

import java.io.IOException;

@WebServlet("/registered")
public class RegisterServlet extends HttpServlet {
    private UserServices userServices;

    @Override
    public void init() throws ServletException {
        userServices = new UserServiceimpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm-password");

        // 验证密码是否一致
        if (!password.equals(confirmPassword)) {
            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("<script>alert('密码不一致，请重新输入');location.href='/register';</script>");
            return;
        }

        // 检查用户名是否已存在
        if (userServices.isUsernameExists(username)) {
            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("<script>alert('用户名已存在，请重新输入');location.href='/register';</script>");
            return;
        }

        // 创建用户对象并保存到数据库
        User user = new User(username, password);
        userServices.register(user);

        // 注册成功后重定向到登录页面
        resp.sendRedirect("/login-page");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
