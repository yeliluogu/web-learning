package top.soft.bookonline.dao.impl;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import top.soft.bookonline.dao.UserDao;
import top.soft.bookonline.util.JdbcUtil;
import top.soft.bookonline.util.Md5Util;



public class UserDaoimpl implements UserDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtil.getDataSource());

    @Override
    public int insertUser(User user) {
        //
        String sql = "insert into t_user(account,password,nickname,avatar,address) values(?,?,?,?,?)";
        return jdbcTemplate.update(sql, user.getAccount(), Md5Util.crypt(user.getPassword()), user.getNickname(), user.getAvatar(), user.getAddress());

    }

    @Override
    public User findUser(User user) {
        try {
            String sql = "select * from t_user where account=? and password=?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user.getAccount(), Md5Util.crypt(user.getPassword()));
        } catch(DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
