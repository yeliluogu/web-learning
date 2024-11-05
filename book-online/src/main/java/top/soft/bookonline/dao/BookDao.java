package top.soft.bookonline.dao;

import top.soft.bookonline.entity.Book;

import java.util.List;

public interface BookDao {
    /**
     *
     * @return
     */
    List<Book> selectAll();


    /**
     *
     * @param id
     * @return
     */
    Book getBookById(int id);
}
