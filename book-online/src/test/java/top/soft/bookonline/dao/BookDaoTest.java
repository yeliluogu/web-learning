package top.soft.bookonline.dao;

import org.junit.jupiter.api.Test;
import top.soft.bookonline.dao.impl.BookDaoimpl;
import top.soft.bookonline.entity.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoTest {

    @Test
    void selectAll() {
        BookDao bookDao=new BookDaoimpl();
        List<Book> books=bookDao.selectAll();
            System.out.println(books.size());

    }

    @Test
    void getBookById() {
        Book book=new BookDaoimpl().getBookById(1);
        System.out.println(book);
    }
}