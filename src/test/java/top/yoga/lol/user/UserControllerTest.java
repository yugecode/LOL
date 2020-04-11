package top.yoga.lol.user;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import top.yoga.lol.user.service.MailService;
import top.yoga.lol.user.vo.LoginReq;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author luojiayu
 * @date 2020/4/9 15:51
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserControllerTest extends BaseTest {
    @Autowired
    private MailService mailService;

    @Test
    public void testSave() throws Exception {
        final LoginReq req = new LoginReq();
        req.setUserName("hello");
        req.setPassword("admin@123");
        req.setCode(mailService.sendMail("532132647@qq.com"));
        final String jsonStr = JSONObject.toJSONString(req);
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonStr)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}