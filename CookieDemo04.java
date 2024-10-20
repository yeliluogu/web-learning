package top.soft.class04.cookie;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author 夜里
 * @description: TODO
 * @date 2024/10/19 14:02
 */
@WebServlet("/cookieDemo04")
public class CookieDemo04 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies){
            String name = cookie.getName();
            if("chineseName".equals(name)){
                String value = cookie.getValue();
                String decode = URLDecoder.decode(value,StandardCharsets.UTF_8);
                System.out.println("用户名:"+decode);
                break;
            }
        }

    }
}
    