package top.soft.class04.cookie;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author 夜里
 * @description: TODO
 * @date 2024/10/19 13:50
 */

@WebServlet("/cookieDemo01")
public class cookieDemo01 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("username", "zhangsan");
        response.addCookie(cookie);
        cookie.setMaxAge(60*60*24*7);

    }
}
