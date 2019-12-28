package top.yoga.lol.user.dao;

import org.apache.ibatis.annotations.Mapper;
import top.yoga.lol.user.entity.User;

/**
 * @author luojiayu
 * @date 2019/12/28 17:12
 */
@Mapper
public interface UserDao {

    /**
     * 通过用户名查询用户信息
     *
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     * 插入用户
     *
     * @param user
     * @return
     */
    int insertUser(User user);
}
