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
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping("/sendMail")
    public ResponseTemplate sendMail(@Email @RequestParam("email") String email) {
        mailService.sendMail(email);
        return ResponseTemplate.ok();
    }
}
