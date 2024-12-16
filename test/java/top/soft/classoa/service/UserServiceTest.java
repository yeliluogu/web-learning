package top.soft.classoa.service;

import org.junit.jupiter.api.Test;
import top.soft.classoa.entity.User;

import javax.security.auth.login.LoginException;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService userService = new UserService();

    @Test
    void login() throws LoginException {
        User login = userService.login("t7", "test");
        System.out.println(login);


    }

//    @Test
//    void notExistUser() throws LoginException {
//        User unkowUser = userService.login("t71", "test");
//        System.out.println(unkowUser);
//    }
//    @Test
//    void errorPassword() throws LoginException {
//        User errorPassword = userService.login("t7", "test1");
//        System.out.println(errorPassword);
//    }
}