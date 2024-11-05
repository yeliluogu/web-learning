package top.soft.bookonline.servlet;


import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.soft.bookonline.entity.Book;
import top.soft.bookonline.service.impl.BookServiceimpl;

import java.io.IOException;

/**
 * @author 86188
 * @description: TODO
 * @date 2024/10/26 15:32
 */
@WebServlet("/detail/*")
public class BookDeailServlet extends HttpServlet {
    private BookServiceimpl bookService;
    @Override
    public void init(ServletConfig config)throws ServletException{
        bookService=new BookServiceimpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPath=req.getRequestURI().trim();
        int position=requestPath.lastIndexOf("/");
        String id=requestPath.substring(position+1);
        Book book=bookService.getBookById(Integer.parseInt(id));
        req.setAttribute("book",book);
        req.getRequestDispatcher("/book_detail.jsp").forward(req,resp);

    }
}