package top.yoga.lol.tweet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.yoga.lol.common.ResponseTemplate;
import top.yoga.lol.tweet.entity.Message;
import top.yoga.lol.tweet.service.TweetService;
import top.yoga.lol.tweet.vo.CommentReq;
import top.yoga.lol.tweet.vo.ReplyReq;
import top.yoga.lol.tweet.vo.TweetDetailsVo;
import top.yoga.lol.tweet.vo.TweetListVo;
import top.yoga.lol.tweet.vo.TweetModifiedReq;
import top.yoga.lol.tweet.vo.TweetReq;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
     * @return
     */
    @RequestMapping(value = "/allUserList", method = RequestMethod.GET)
    public ResponseTemplate<TweetListVo> listAllUserTweet() {
        return ResponseTemplate.ok(tweetService.listAllUserTweet());
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

    /**
     * 帖子点赞和取消点赞
     *
     * @param tweetId
     * @param userId
     * @return {@link String}
     * @author luojiayu
     * @date 2020/1/10
     */
    @RequestMapping(value = "/clickTumbups", method = RequestMethod.GET)
    public ResponseTemplate<String> clickTumbups(@RequestParam("tweetId") Integer tweetId,
                                                 @RequestParam("userId") Integer userId) {
        String msg = tweetService.clickTumbups(tweetId, userId);
        return ResponseTemplate.ok(msg);
    }

    /**
     * 发送回复评论
     *
     * @param replyReq
     * @return
     * @author luojiayu
     * @date 2020/1/13
     */
    @RequestMapping(value = "/reply", method = RequestMethod.POST)
    public ResponseTemplate sendReply(@Validated @RequestBody ReplyReq replyReq) {
        tweetService.sendReply(replyReq);
        return ResponseTemplate.ok();
    }

    /**
     * 获取帖子详情信息
     *
     * @param tweetId 帖子id
     * @return
     * @author luojiayu
     * @date 2020/1/13
     */
    @RequestMapping(value = "/getDetails", method = RequestMethod.GET)
    public ResponseTemplate<TweetDetailsVo> getTweetDetails(@RequestParam("tweetId") Integer tweetId) {
        return ResponseTemplate.ok(tweetService.getTweetDetails(tweetId));
    }

    /**
     * 修改帖子信息
     *
     * @param req
     * @return
     * @author luojiayu
     * @date 2020/1/15
     */
    @RequestMapping(value = "/modified", method = RequestMethod.PUT)
    public ResponseTemplate modifiedTweet(@Validated @RequestBody TweetModifiedReq req) {
        tweetService.modifiedTweet(req);
        return ResponseTemplate.ok();
    }

    /**
     * 删除自己发布的帖子
     *
     * @param tweetId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public ResponseTemplate delTweet(@RequestParam("tweetId") Integer tweetId,
                                     @RequestParam("userId") Integer userId) {
        tweetService.delTweet(tweetId, userId);
        return ResponseTemplate.ok();
    }

    /**
     * 删除自己的评论
     *
     * @param tweetId
     * @param commentId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/delComment", method = RequestMethod.DELETE)
    public ResponseTemplate delComment(@RequestParam("tweetId") Integer tweetId,
                                       @RequestParam("commentId") Integer commentId,
                                       @RequestParam("userId") Integer userId) {
        tweetService.delComment(tweetId, commentId, userId);
        return ResponseTemplate.ok();
    }

    /**
     * 删除自己的回复
     *
     * @param tweetId
     * @param commentId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/delReply", method = RequestMethod.DELETE)
    public ResponseTemplate delReply(@RequestParam("tweetId") Integer tweetId,
                                     @RequestParam("commentId") Integer commentId,
                                     @RequestParam("replyId") Integer replyId,
                                     @RequestParam("userId") Integer userId) {
        tweetService.delReply(tweetId, commentId, replyId, userId);
        return ResponseTemplate.ok();
    }

    /**
     * 获取用户的消息列表
     *
     * @return
     */
    @GetMapping("/messageList")
    public ResponseTemplate<List<Message>> getMessageList() {
        return ResponseTemplate.ok(tweetService.getMessageList());
    }

    /**
     * 消息详情
     *
     * @param messageId
     * @return
     */
    @GetMapping("/messageDetails")
    public ResponseTemplate<TweetDetailsVo> getMessageDetails(Integer messageId) {
        return ResponseTemplate.ok(tweetService.getMessageDetails(messageId));
    }

}
