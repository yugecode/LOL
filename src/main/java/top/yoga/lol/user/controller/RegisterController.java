package top.yoga.lol.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yoga.lol.common.ResponseTemplate;
import top.yoga.lol.user.service.RegisterService;
import top.yoga.lol.user.vo.RegisterReq;

/**
 * 登录
 *
 * @author luojiayu
 * @date 2019/12/27 16:53
 */
@RestController
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public ResponseTemplate register(@Validated @RequestBody RegisterReq registerReq) {
        registerService.register(registerReq);
        return ResponseTemplate.ok();
    }
}
