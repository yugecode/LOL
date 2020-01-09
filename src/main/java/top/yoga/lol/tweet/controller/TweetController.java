package top.yoga.lol.tweet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.yoga.lol.common.ResponseTemplate;
import top.yoga.lol.tweet.service.TweetService;
import top.yoga.lol.tweet.vo.CommentReq;
import top.yoga.lol.tweet.vo.TweetListVo;
import top.yoga.lol.tweet.vo.TweetReq;

/**
 * 帖子
 *
 * @author luojiayu
 * @date 2020/1/8 16:09
 */
@RestController
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    /**
     * 发帖
     *
     * @param tweetReq 发帖请求体
     * @return {@link ResponseTemplate}
     * @author luojiayu
     * @date 2020/1/8
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public ResponseTemplate sendTweet(@Validated @RequestBody TweetReq tweetReq) {
        tweetService.sendTweet(tweetReq);
        return ResponseTemplate.ok();
    }

    /**
     * 获取全部帖子列表
     *
     * @return {@link TweetListVo}
     * @author luojiayu
     * @date 2020/1/9
     */
    @RequestMapping(value = "/allList", method = RequestMethod.GET)
    public ResponseTemplate<TweetListVo> listAllTweet() {
        return ResponseTemplate.ok(tweetService.listAllTweet());
    }

    /**
     * 查询当前用户下的帖子列表
     *
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value = "/allUserList", method = RequestMethod.GET)
    public ResponseTemplate<TweetListVo> listAllUserTweet(@RequestParam("userId") Integer userId) {
        return ResponseTemplate.ok(tweetService.listAllUserTweet(userId));
    }

    /**
     * 评论帖子
     *
     * @param commentReq 评论请求体
     * @return {@link ResponseTemplate}
     * @author luojiayu
     * @date 2020/1/9
     */
    @RequestMapping(value = "/sendContent", method = RequestMethod.POST)
    public ResponseTemplate sendContent(@Validated @RequestBody CommentReq commentReq) {
        tweetService.sendContent(commentReq);
        return ResponseTemplate.ok();
    }
}
