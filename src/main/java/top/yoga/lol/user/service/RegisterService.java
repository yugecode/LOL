package top.yoga.lol.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yoga.lol.common.exception.AppException;
import top.yoga.lol.common.utils.RedisUtils;
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


    }
}
