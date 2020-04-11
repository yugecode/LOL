package top.yoga.lol.tweet;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.yoga.lol.tweet.service.TweetService;
import top.yoga.lol.tweet.vo.CommentReq;
import top.yoga.lol.tweet.vo.ReplyReq;
import top.yoga.lol.tweet.vo.TweetReq;
import top.yoga.lol.user.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author luojiayu
 * @date 2020/4/9 16:38
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TweetTest {

    @Autowired
    private TweetService tweetService;

    private MockHttpServletRequest request;

    @Before
    public void setUser(){
        User user = new User();
        user.setId(1);
        user.setUserName("ypgac");
        user.setPassword("admin@123");
        user.setEmail("ljy_0624@qq.com");
        //设置属性user
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("user", user);
    }

    @Test
    public void sendTweetTest(){
        TweetReq req = new TweetReq();
        req.setUserName("yogac");
        req.setContent("单元测试发帖");
        req.setTitle("帖子测试");
        req.setUserId(1);
        tweetService.sendTweet(req);
    }

    @Test
    public void sendCommentTest(){
        CommentReq req = new CommentReq();
        req.setContent("单元测试评论");
        req.setTweetId(1);
        req.setUserId(1);
        req.setUserBid(1);
        tweetService.sendContent(req);
    }

    @Test
    public void sendReply(){
        ReplyReq req = ReplyReq.builder()
            .commentId(1)
            .content("单元测试回复")
            .tweetId(1)
            .userId(1)
            .userBid(1)
            .userBName("yogac")
            .userName("yogac")
            .build();
        tweetService.sendReply(req);
    }
}
