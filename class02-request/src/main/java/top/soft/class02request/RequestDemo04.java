package top.soft.class02request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@WebServlet("/requestDemo04")
public class RequestDemo04 extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       根据参数名获取参数值
        String username = req.getParameter("username");
        System.out.println("username: " + username);
//        根据参数名获取参数值的数组
        String [] hobbies = req.getParameterValues("hobby");
        for (String hobby : hobbies){
            System.out.println("hobby: " + hobby);
        }
//        获取所有参数的参数名称
        System.out.println("==========");
        System.out.println("获取所有请求的参数名称");
        Enumeration<String>paramNames = req.getParameterNames();
        while(paramNames.hasMoreElements()){
            String paramName = paramNames.nextElement();
            System.out.println("参数名 " +paramName);
            String value = req.getParameter(paramName);
            System.out.println("参数值"+value);
        }
        System.out.println("==========");
        System.out.println("获取所有请求的map集合");
        Map<String,String[]>paramMap = req.getParameterMap();
        Set<String> keySet=paramMap.keySet();
        for (String name : keySet){
            System.out.println("参数名称："+name);
            String[] values = paramMap.get(name);
            for (String value : values){
                System.out.println(value);
            }
        }
    }
}