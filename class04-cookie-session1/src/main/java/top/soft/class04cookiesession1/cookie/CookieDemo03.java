package top.soft.class04.cookie;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author 夜里
 * @description: TODO
 * @date 2024/10/19 14:17
 */

@WebServlet("/cookieDemo03")
public class CookieDemo03 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value ="张三";
        Cookie cookie = new Cookie("chineseName", URLEncoder.encode(value, StandardCharsets.UTF_8));
        response.addCookie(cookie);
    }
}
