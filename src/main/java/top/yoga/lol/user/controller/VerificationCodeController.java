package top.yoga.lol.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.yoga.lol.common.ResponseTemplate;
import top.yoga.lol.user.service.MailService;

import javax.validation.constraints.Email;

/**
 * 邮件服务
 *
 * @author luojiayu
 * @date 2019/12/27 15:31
 */
@RestController
public class VerificationCodeController {

    @Autowired
    private MailService mailService;

    /**
     * 发送邮件
     *
     * @param email 接收方邮箱地址
     * @return {@link ResponseTemplate}
     * @author luojiayu
     * @date 2020/1/7
     */
    @GetMapping("/sendMail")
    public ResponseTemplate sendMail(@Email @RequestParam("email") String email) {
        mailService.sendMail(email);
        return ResponseTemplate.ok();
    }

    @GetMapping("/sendCode")
    public ResponseTemplate sendCode(String userName){
        mailService.sendCode(userName);
        return ResponseTemplate.ok();
    }
}
