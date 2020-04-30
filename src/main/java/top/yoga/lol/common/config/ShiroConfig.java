package top.yoga.lol.common.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.yoga.lol.common.filter.MyFilter;
import top.yoga.lol.common.auth.MyRealm;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置
 *
 * @author luojiayu
 * @date 2020/1/7 15:19
 */
@Slf4j
@Configuration
public class ShiroConfig {

    /**
     * 注入 Shiro 过滤器
     *
     * @param securityManager 安全管理器
     * @return ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        /**
         * 需要配置以下四个：
         * 1.默认登录的 URL：身份认证失败会访问该 URL。
         * 2.认证成功之后要跳转的 URL。
         * 3.权限认证失败后要跳转的 URL。
         * 4.需要拦截或者放行的 URL：这些都放在一个 Map 中。
         */
        Map<String, Filter> mapFilter = new LinkedHashMap<>();
        mapFilter.put("authc", myAuthFilter());
        // 定义 shiroFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 设置自定义的 securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 设置默认登录的 URL，身份认证失败会访问该 URL
        shiroFilterFactoryBean.setLoginUrl("/user/login");
        // 设置成功之后要跳转的链接
        //shiroFilterFactoryBean.setSuccessUrl("/success");
        // 设置未授权界面，权限认证失败会访问该 URL
        //shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

        // LinkedHashMap 是有序的，进行顺序拦截器配置
        Map<String, String> filterChainMap = new LinkedHashMap<>();
//        filterChainMap.put("perms",new MyFilter().toString());

        // 配置可以匿名访问的地址，可以根据实际情况自己添加，放行一些静态资源等，anon 表示放行
        filterChainMap.put("/css/**", "anon");
        filterChainMap.put("/imgs/**", "anon");
        filterChainMap.put("/js/**", "anon");
        filterChainMap.put("/swagger-*/**", "anon");
        filterChainMap.put("/swagger-ui.html/**", "anon");
        //放行发邮件
        filterChainMap.put("/sendMail", "anon");
        // 登录 URL 放行
        filterChainMap.put("/user/login", "anon");
        filterChainMap.put("/user/forgetPassword", "anon");
        filterChainMap.put("/user/register", "anon");
        // 以“/info/” 开头的用户需要身份认证，authc 表示要进行身份认证
        //英雄联盟信息接口
//        filterChainMap.put("/info/**", "anon");
        filterChainMap.put("/info/**", "anon");
        //帖子相关接口进行拦截
        filterChainMap.put("/tweet/**", "anon");
        // 修改密码需要身份认证
        filterChainMap.put("/user/logout", "authc");
        filterChainMap.put("/user/modifyPassword", "authc");
        // 配置 logout 过滤器
        filterChainMap.put("/logout", "logout");
        // 设置 shiroFilterFactoryBean 的 FilterChainDefinitionMap
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        shiroFilterFactoryBean.setFilters(mapFilter);
        log.info("====shiroFilterFactoryBean注册完成====");
        return shiroFilterFactoryBean;
    }

    /**
     * 注入安全管理器
     *
     * @return SecurityManager
     */
    @Bean
    public SecurityManager securityManager() {
        // 将自定义 Realm 加进来
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(myAuthRealm());
        log.info("====securityManager注册完成====");
        return securityManager;
    }

    /**
     * 注入自定义的Realm
     *
     * @return
     */
    @Bean
    public MyRealm myAuthRealm() {
        MyRealm myRealm = new MyRealm();
        log.info("======MyRealm注册完成======");
        return myRealm;
    }

    /**
     * 自定义的拦截器
     *
     * @return {@link MyFilter}
     * @author luojiayu
     * @date 2020/1/8
     */
    @Bean
    public MyFilter myAuthFilter() {
        return new MyFilter();
    }
}
