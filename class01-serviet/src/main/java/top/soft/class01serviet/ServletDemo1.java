package top.soft.class01serviet;

import jakarta.servlet.*;

import java.io.IOException;

public class ServletDemo1 implements Servlet {
    /**
     *
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
System.out.println("Servlet 初始化");
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
servletResponse.getWriter().write("servletDemo1 执行");

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