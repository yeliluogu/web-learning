package top.soft.bookonline.dao;

public interface UserDao {
    /**
     *
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     *
     * @param user
     * @return
     */

    User findUser(User user);
}
