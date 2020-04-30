package top.yoga.lol.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.yoga.lol.user.entity.User;

/**
 * 用户持久层
 *
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
     * 通过用户名和密码获取用户信息
     *
     * @param name
     * @param password
     * @return
     */
    User getUser(@Param("name") String name,
                 @Param("password") String password);

    /**
     * 通过id用户信息
     *
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    int modifyUser(User user);

    /**
     * 插入用户
     *
     * @param user
     * @return
     */
    int insertUser(User user);
}
