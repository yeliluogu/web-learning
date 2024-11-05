package top.soft.bookonline.dao;

import org.junit.jupiter.api.Test;
import top.soft.bookonline.dao.impl.UserDaoimpl;

class UserDaoTest {

    @Test
    void insertUser() {
        UserDao userDao = new UserDaoimpl();
        User user = User.builder().account("zyf").nickname("zyf").password("123456").address("江苏南京").avatar("C:/Users/夜里/Desktop/20240807173452_1.jpg").build();
        userDao.insertUser(user);
    }
}