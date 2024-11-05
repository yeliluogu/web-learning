package top.soft.bookonline.service.impl;

import top.soft.bookonline.service.UserServices;

import java.util.ArrayList;
import java.util.List;

public class UserServiceimpl implements UserServices {
    private static final List<User> users = new ArrayList<>();

    @Override
    public User signIn(String account, String password) {
        for (User user : users) {
            if (user.getAccount().equals(account) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean isUsernameExists(String account) {
        for (User user : users) {
            if (user.getAccount().equals(account)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void register(User user) {
        users.add(user);
    }
}
