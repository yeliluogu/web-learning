package top.soft.class02request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/requestDemo1")
public class RequestDemo01 extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //获取请求方法
        String method= req.getMethod();
        System.out.println("获取请求方法"+method);
        //获取Servlet路径
        String servletPath=req.getServletPath();
        System.out.println("获取Servlet路径"+servletPath);
        //获取get请求参数
        String queryString= req.getQueryString();
        System.out.println("获取请求参数"+queryString);
        //获取请求GuI和URL
        String requestURI=req.getRequestURI();
        System.out.println("获取请求URI"+requestURI);
        StringBuffer requestURl=req.getRequestURL();
        System.out.println("获取请求URl"+requestURl);
        //获取版本协议
        String protocol=req.getProtocol();
        System.out.println("获取版本协议"+protocol);
        //获取虚拟目录
        String contextpath=req.getContextPath();
        System.out.println("获取虚拟目录"+contextpath);
    }
}
