package top.yoga.lol.user.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 发送邮件工具类
 *
 * @author luojiayu
 * @date 2019/12/27 14:54
 */
@Slf4j
@Component
public class MailUtils {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.fromMail.addr}")
    private String from;

    /**
     * 传输一个邮件对象
     *
     * @param to 邮箱接受者
     */
    public int sendMail(String to) {
        int code = getRandom(100000, 999999);
        String message = "您好，邮箱验证码是：" + code + ",5分钟内有效!";
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject("验证码");
        mailMessage.setText(message);
        try {
            mailSender.send(mailMessage);
            log.info("发送验证码成功,接受者：{}，验证码：{}", to, code);
        } catch (Exception e) {
            log.info("发送验证码失败");
        }
        return code;
    }

    /**
     * 生成邮箱随机六位验证码
     *
     * @return
     */
    public synchronized int getRandom(int start, int end) {
        Random random = new Random();
        return random.nextInt(end - start) + start + 1;
    }
}
