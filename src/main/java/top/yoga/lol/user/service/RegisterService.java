package top.yoga.lol.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yoga.lol.common.exception.AppException;
import top.yoga.lol.common.utils.RedisUtils;
import top.yoga.lol.user.dao.UserDao;
import top.yoga.lol.user.entity.User;
import top.yoga.lol.user.vo.RegisterReq;

/**
 * @author luojiayu
 * @date 2019/12/27 16:53
 */
@Service
@Slf4j
public class RegisterService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserDao userDao;

    /**
     * 注册
     *
     * @param registerReq
     */
    public void register(RegisterReq registerReq) {
        String email = registerReq.getEmail();
        Integer code = registerReq.getCode();
        Integer redisCode = (Integer) redisUtils.get(email);
        if (!code.equals(redisCode)) {
            log.info("接受者：{}，验证码：{}", email, code);
            throw new AppException("验证码失效!");
        }
        //验证码验证成功进行注册
        //查询是否存在该名字
        User userDB = userDao.getUserByName(registerReq.getUserName());
        if (null != userDB) {
            throw new AppException("该用户名已存在，请重新注册!");
        }
        User user = new User();
        BeanUtils.copyProperties(registerReq, user);
        userDao.insertUser(user);
        log.info("注册用户信息为：{}", user);
    }
}
