package top.yoga.lol.common.auth;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import top.yoga.lol.user.dao.UserDao;
import top.yoga.lol.user.entity.User;

/**
 * 自定义的Realm
 *
 * @author luojiayu
 * @date 2020/1/7
 */
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    /**
     * 授权
     *
     * @param principalCollection
     * @return {@link AuthorizationInfo}
     * @author luojiayu
     * @date 2020/1/7
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //以下通过用户名username获取相应角色及其权限
        //给用户设置角色，角色信息存在t_role表中
//        authorizationInfo.setRoles(userService.getRoles(username));
//        //给用户设置权限，权限信息存在t_permission表中
//        authorizationInfo.setStringPermissions(userService.getPermissions(username));
        return authorizationInfo;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return {@link AuthenticationInfo}
     * @author luojiayu
     * @date 2020/1/7
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //根据token获取用户名
        String username = (String) authenticationToken.getPrincipal();
        //根据用户名从数据库表中获取该用户
        User user = userDao.getUserByName(username);
        if (user != null) {
            // 把当前用户存到 Session 中
            SecurityUtils.getSubject().getSession().setAttribute("user", user);
            // Objects.requireNonNull(HttpContext.getRequest()).getSession()
            // 传入用户名和密码进行身份认证，并返回认证信息
            log.info("MyRealm中的用户信息：{}", user);
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), "myRealm");
            return authcInfo;
        } else {
            return null;
        }
    }
}
