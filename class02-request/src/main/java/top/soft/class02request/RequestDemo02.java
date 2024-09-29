package top.soft.class02request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/requestDemo2")
public class RequestDemo02 extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //获取请求头数据
        //遍历所有所有请求头数据
        Enumeration<String> headNames= req.getHeaderNames();
        while (headNames.hasMoreElements()){
            String headName=headNames.nextElement();
            //根据名称获取请求头的值
            String headValue=req.getHeader(headName);
        }
       String header=req.getHeader("user-agent");
        if(header.contains("Edg")){
            System.out.println("Edge浏览器");
        }else{
            System.out.println("Chrome浏览器");
        }
    }
}
