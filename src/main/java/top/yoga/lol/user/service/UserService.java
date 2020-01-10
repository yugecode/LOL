package top.yoga.lol.user.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yoga.lol.common.exception.AppException;
import top.yoga.lol.common.utils.PasswordUtils;
import top.yoga.lol.common.utils.RedisUtils;
import top.yoga.lol.user.dao.UserDao;
import top.yoga.lol.user.entity.User;
import top.yoga.lol.user.utils.MailUtils;
import top.yoga.lol.user.utils.UserUtils;
import top.yoga.lol.user.vo.ForgetPasswordReq;
import top.yoga.lol.user.vo.LoginReq;
import top.yoga.lol.user.vo.ModifyReq;
import top.yoga.lol.user.vo.RegisterReq;
import top.yoga.lol.user.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author luojiayu
 * @date 2019/12/27 16:53
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordUtils passwordUtils;

    /**
     * 注册
     *
     * @param registerReq
     */
    @Transactional
    public void register(RegisterReq registerReq) {
        log.info("注册请求参数：{}", registerReq);
        //查询是否存在该名字
        User userDB = userDao.getUserByName(registerReq.getUserName());
        if (null != userDB) {
            throw new AppException("该用户名已存在，请重新注册!");
        }
        String email = registerReq.getEmail();
        Integer code = registerReq.getCode();
        Integer redisCode = (Integer) redisUtils.get(email);
        if (!code.equals(redisCode)) {
            log.info("接受者：{}，验证码：{}", email, code);
            throw new AppException("验证码失效!");
        }
        if (!registerReq.getPassword().equals(registerReq.getRe_password())) {
            throw new AppException("两次密码不一致，请重新输入!");
        }
        //验证码验证成功进行注册
        User user = new User();
        BeanUtils.copyProperties(registerReq, user);
        //将用户信息放入数据库
        userDao.insertUser(user);
        //修改用户信息，将加密的密码存到数据库
        log.info("当前用户的id:{}", user.getId());
        String password = passwordUtils.encodePassword(user.getPassword(), String.valueOf(user.getId()));
        log.info("加密后的密码为：{}", password);
        user.setPassword(password);
        //将加密的密码存入数据库
        userDao.modifyUser(user);
        //从redis中将验证码给删除
        redisUtils.del(email);
        log.info("注册用户信息为：{}", user);
    }

    /**
     * 登录逻辑
     *
     * @param loginReq 登录请求体
     * @param request
     * @return {@link String}
     * @author luojiayu
     * @date 2020/1/7
     */
    @Transactional
    public UserVo login(LoginReq loginReq, HttpServletRequest request) {
        log.info("登录请求参数：{}", loginReq);
        User user = userDao.getUserByName(loginReq.getUserName());
        if (null == user) {
            throw new AppException("该用户未进行注册");
        }
        //获取验证码
        Integer code = (Integer) redisUtils.get(user.getEmail());
        if (null == code) {
            throw new AppException("验证码失效");
        }
        if (!loginReq.getCode().equals(code)) {
            throw new AppException("验证码输入错误");
        }
        redisUtils.del(user.getEmail());
        UserVo userVo = new UserVo();
        //将密码进行加密
        String password = passwordUtils.encodePassword(loginReq.getPassword(), String.valueOf(user.getId()));
        // 根据用户名和密码创建 Token
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), password);
        // 获取 subject 认证主体
        Subject subject = UserUtils.getSubject();
        try {
            // 开始认证，这一步会跳到我们自定义的 Realm 中
            subject.login(token);
            request.getSession().setAttribute("user", user);
            BeanUtils.copyProperties(user, userVo);
            log.info("当前用户信息：{}", userVo);
            return userVo;
        } catch (UnknownAccountException e) {
            throw new AppException("用户名不存在");
        } catch (IncorrectCredentialsException e) {
            throw new AppException("密码错误");
        }
    }

    /**
     * 用户登出
     *
     * @author luojiayu
     * @date 2020/1/7
     */
    public void logout() {
        User user = UserUtils.getUserInfo();
        Subject subject = UserUtils.getSubject();
        if (null == user) {
            throw new AppException("未登录，无法进行退出");
        }
        subject.logout();
        log.info("当前用户信息为：{}", user);
        log.info("用户退出。。。");
    }

    /**
     * 修改密码
     *
     * @param modifyReq 修改密码请求体
     * @author luojiayu
     * @date 2020/1/7
     */
    @Transactional
    public void modifyPassword(ModifyReq modifyReq) {
        log.info("修改密码参数：{}", modifyReq);
        User user = UserUtils.getUserInfo();
        if (null == user) {
            throw new AppException("请登录");
        }
        //将修改的密码加密存到数据库中
        String password = passwordUtils.encodePassword(modifyReq.getModifyPassword(), String.valueOf(user.getId()));
        User user1 = User.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(password)
                .build();
        log.info("修改后的用户信息：{}", user1);
        userDao.modifyUser(user1);
        //改完密码之后退出登录
        UserUtils.removeSession();
        UserUtils.getSubject().logout();
    }

    /**
     * 忘记密码
     *
     * @param forgetPasswordReq 忘记密码请求体
     * @author luojiayu
     * @date 2020/1/8
     */
    @Transactional
    public void forgetPassword(ForgetPasswordReq forgetPasswordReq) {
        log.info("请求参数：{}", forgetPasswordReq);
        String email = forgetPasswordReq.getEmail();
        String password = forgetPasswordReq.getNewPassword();
        User user_db = userDao.getUserByName(forgetPasswordReq.getUserName());
        if (null == user_db) {
            throw new AppException("该用户不存在");
        }
        if (!forgetPasswordReq.getEmail().equals(user_db.getEmail())) {
            throw new AppException("用户邮箱地址不正确");
        }
        //从redis取出验证码
        Integer redisCode = (Integer) redisUtils.get(email);
        //删除缓存
        redisUtils.del(email);
        if (!forgetPasswordReq.getCode().equals(redisCode)) {
            throw new AppException("验证码不正确");
        }
        if (!password.equals(forgetPasswordReq.getConfirmPassword())) {
            throw new AppException("两次密码不一致");
        }
        User user = new User();
        user.setPassword(passwordUtils.encodePassword(password, String.valueOf(user_db.getId())));
        user.setEmail(email);
        user.setId(user_db.getId());
        user.setUserName(user_db.getUserName());
        log.info("修改后的用户信息为：{}", user);
        userDao.modifyUser(user);
    }
}
