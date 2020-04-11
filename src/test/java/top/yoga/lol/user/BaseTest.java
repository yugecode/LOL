package top.yoga.lol.user;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import top.yoga.lol.user.service.MailService;
import top.yoga.lol.user.vo.LoginReq;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author luojiayu
 * @date 2020/4/9 15:46
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BaseTest {

    @Autowired
    org.apache.shiro.mgt.SecurityManager securityManager;

    @Autowired
    WebApplicationContext webApplicationContext;

    public MockMvc mockMvc;

    @Before
    public void before() {
        SecurityUtils.setSecurityManager(securityManager);
        mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .build();
        final UsernamePasswordToken token = new UsernamePasswordToken("hello", "admin@123");
        final Subject subject = SecurityUtils.getSubject();

        subject.login(token);
    }

}


