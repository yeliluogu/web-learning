package top.soft.class01serviet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
@WebServlet(value = "/demo3",name = "demo3")
public class ServletDemo3 implements Servlet {
    /**
     *
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
System.out.println("Servletdemo3 初始化");
    }

    /**
     *
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 执行Service 方法
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
servletResponse.getWriter().write("servletDemo3 执行");
System.out.println("servletDemo3 执行" );
    }

    /**
     *
     * @return
     */
    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {
        System.out.println("销毁方法");
    }
}