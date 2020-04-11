package top.yoga.lol.user;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.WebSubject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.yoga.lol.user.service.MailService;
import top.yoga.lol.user.service.UserService;
import top.yoga.lol.user.utils.MailUtils;
import top.yoga.lol.user.vo.LoginReq;
import top.yoga.lol.user.vo.RegisterReq;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户模块单元测试
 * 在测试时需要生成随机端口号避免和websocket端口冲突
 *
 * @author luojiayu
 * @date 2020/4/9 13:21
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestUser {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    private MockHttpServletRequest request;
    @Autowired
    private SecurityManager securityManager;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private SessionDAO sessionDAO;
    private Subject subject;
    private MockMvc mockMvc;
//
//    @Before
//    public void getRequest() {
//
//    }

    /**
     * 用户注册测试
     */
    @Test
    public void registerTest() {
        //发送邮箱验证码
        int code = mailService.sendMail("532132647@qq.com");
        RegisterReq req = new RegisterReq();
        req.setUserName("hello");
        req.setEmail("532132647@qq.com");
        req.setPassword("admin@123");
        req.setRe_password("admin@123");
        req.setCode(code);
        log.info("注册信息：{}", req);
        userService.register(req);
    }

    @Test
    public void loginTest() {
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
//        MockHttpSession mockHttpSession = new MockHttpSession(webApplicationContext.getServletContext());
//        MockHttpServletResponse response = new MockHttpServletResponse();
//        request.setSession(mockHttpSession);
        SecurityUtils.setSecurityManager(securityManager);
//        mockMvc = MockMvcBuilders
//            .webAppContextSetup(webApplicationContext)
//            .build();
//        subject = new WebSubject.Builder(request, response)
//            .buildWebSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken("hello", "admin@123", true);
//        subject.login(token);

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("hello","admin@123", false,request.getRemoteAddr());
        currentUser.login(token);
    }

}
