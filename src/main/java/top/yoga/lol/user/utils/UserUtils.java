package top.yoga.lol.user.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import top.yoga.lol.user.entity.User;

/**
 * 用户工具类
 *
 * @author luojiayu
 * @date 2020/1/8 18:04
 */
public class UserUtils {
    /**
     * 认证主体
     */
//    private static Subject subject = SecurityUtils.getSubject();

    /**
     * 获取当前用户
     *
     * @return {@link User}
     * @author luojiayu
     * @date 2020/1/8
     */
    public static User getUserInfo() {
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute("user");
        return user;
    }

    /**
     * 获取认证主体
     *
     * @return {@link Subject}
     * @author luojiayu
     * @date 2020/1/8
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 移除session
     */
    public static void removeSession() {
        SecurityUtils.getSubject().getSession().removeAttribute("user");
    }
}
