package top.soft.bookonline.service;

import top.soft.bookonline.entity.Book;

import java.util.List;

public interface BookService {
    /**
     *
     * @return
     */
    List<Book> getBook();

    /**
     *
     * @param id
     * @return
     */
    Book getBookById(int id);
}
