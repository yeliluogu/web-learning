package top.soft.bookonline.service.impl;


import top.soft.bookonline.dao.BookDao;
import top.soft.bookonline.dao.impl.BookDaoimpl;
import top.soft.bookonline.entity.Book;
import top.soft.bookonline.service.BookService;

import java.util.List;

/**
 * @author 86188
 * @description: TODO
 * @date 2024/10/26 14:27
 */

public class BookServiceimpl implements BookService {
    private final BookDao bookDao = new BookDaoimpl();

    /**
     *
     * @return
     */
    @Override
    public List<Book> getBook() {
        return bookDao.selectAll();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Book getBookById(int id) {
        return bookDao.getBookById(id);
    }
}