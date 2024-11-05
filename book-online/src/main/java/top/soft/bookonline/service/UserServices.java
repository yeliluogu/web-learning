package top.soft.bookonline.service;

public interface UserServices {
    /**
     *
     * @param account
     * @param password
     * @return
     */
    User signIn(String account,String password);

    boolean isUsernameExists(String account);

    void register(User user);
}
