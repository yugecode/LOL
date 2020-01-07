package top.yoga.lol.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yoga.lol.common.utils.RedisUtils;
import top.yoga.lol.user.utils.MailUtils;

/**
 * 邮件信息逻辑层
 *
 * @author luojiayu
 * @date 2019/12/27 15:58
 */
@Slf4j
@Service
public class MailService {

    @Autowired
    private MailUtils mailUtils;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 发送邮件
     *
     * @param to
     */
    public void sendMail(String to) {
        int code = mailUtils.sendMail(to);
        //将用户的email和验证码存入redis中
        redisUtils.set(to, code, 300);
    }

    /**
     * 发送验证码
     *
     * @return {@link Integer}
     * @author luojiayu
     * @date 2020/1/7
     */
    public Integer sendCode() {
        Integer code = mailUtils.getRandom(1000, 9999);
        redisUtils.set("code", code, 60);
        log.info("当前的验证码为：{}", code);
        return code;
    }
}
