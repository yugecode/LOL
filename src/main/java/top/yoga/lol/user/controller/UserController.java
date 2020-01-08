package top.yoga.lol.user.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.yoga.lol.common.ResponseTemplate;
import top.yoga.lol.common.exception.AppException;
import top.yoga.lol.common.utils.PasswordUtils;
import top.yoga.lol.user.entity.User;
import top.yoga.lol.user.service.UserService;
import top.yoga.lol.user.vo.ForgetPasswordReq;
import top.yoga.lol.user.vo.LoginReq;
import top.yoga.lol.user.vo.ModifyReq;
import top.yoga.lol.user.vo.RegisterReq;
import top.yoga.lol.user.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

/**
 * 用户注册控制层
 *
 * @author luojiayu
 * @date 2019/12/27 16:53
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordUtils passwordUtils;

    /**
     * 用户注册
     *
     * @param registerReq 用户注册请求类
     * @return {@link ResponseTemplate}
     * @author luojiayu
     * @date 2020/1/7
     */
    @PostMapping("/register")
    public ResponseTemplate register(@Validated @RequestBody RegisterReq registerReq) {
        userService.register(registerReq);
        return ResponseTemplate.ok();
    }

    /**
     * 用户登录
     *
     * @param loginReq
     * @param request
     * @return {@link UserVo}
     * @author luojiayu
     * @date 2020/1/7
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseTemplate<UserVo> login(@RequestBody @Validated LoginReq loginReq, HttpServletRequest request) {
        return ResponseTemplate.ok(userService.login(loginReq, request));
    }

    /**
     * 用户登出
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseTemplate logout() {
        userService.logout();
        return ResponseTemplate.ok();
    }

    /**
     * 修改密码
     *
     * @param modifyReq 修改密码请求参数
     * @return {@link ResponseTemplate}
     * @author luojiayu
     * @date 2020/1/7
     */
    @RequestMapping(value = "/modifyPassword", method = RequestMethod.PUT)
    public ResponseTemplate modifyPassword(@Validated @RequestBody ModifyReq modifyReq) {
        userService.modifyPassword(modifyReq);
        return ResponseTemplate.ok();
    }

    /**
     * 忘记密码
     *
     * @param forgetPasswordReq 忘记密码请求体
     * @return {@link ResponseTemplate}
     * @author luojiayu
     * @date 2020/1/8
     */
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public ResponseTemplate forgetPassword(@Validated @RequestBody ForgetPasswordReq forgetPasswordReq) {
        userService.forgetPassword(forgetPasswordReq);
        return ResponseTemplate.ok();
    }
}
