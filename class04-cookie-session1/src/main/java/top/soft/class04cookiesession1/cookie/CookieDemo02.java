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
 * @date 2024/10/19 14:02
 */
@WebServlet("/cookieDemo02")
public class CookieDemo02 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies){
            String name = cookie.getName();
            if("username".equals(name)){
                System.out.println("value的结果"+cookie.getValue());
                break;
            }
        }

    }
}
