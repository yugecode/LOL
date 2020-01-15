package top.yoga.lol.tweet.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;
import top.yoga.lol.common.exception.AppException;
import top.yoga.lol.common.page.PageQueryBean;
import top.yoga.lol.tweet.dao.CommentDao;
import top.yoga.lol.tweet.dao.ReplyDao;
import top.yoga.lol.tweet.dao.TumbupsDao;
import top.yoga.lol.tweet.dao.TweetDao;
import top.yoga.lol.tweet.entity.Comment;
import top.yoga.lol.tweet.entity.Tumbups;
import top.yoga.lol.tweet.entity.Tweet;
import top.yoga.lol.tweet.enums.TumupusEnum;
import top.yoga.lol.tweet.vo.CommentReq;
import top.yoga.lol.tweet.vo.ReplyReq;
import top.yoga.lol.tweet.vo.TweetDetailsVo;
import top.yoga.lol.tweet.vo.TweetListVo;
import top.yoga.lol.tweet.vo.TweetModifiedReq;
import top.yoga.lol.tweet.vo.TweetReq;
import top.yoga.lol.user.dao.UserDao;
import top.yoga.lol.user.entity.User;
import top.yoga.lol.user.utils.UserUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 帖子逻辑层
 *
 * @author luojiayu
 * @date 2020/1/8 16:23
 */
@Service
@Slf4j
public class TweetService {

    @Autowired
    private TweetDao tweetDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private TumbupsDao tumbupsDao;

    @Autowired
    private ReplyDao replyDao;

    /**
     * 发帖
     *
     * @param tweetReq 发帖信息
     */
    public void sendTweet(TweetReq tweetReq) {
        log.info("请求参数为：{}", tweetReq);
        User user = UserUtils.getUserInfo();
        if (!tweetReq.getUserId().equals(user.getId())) {
            throw new AppException("当前账号不一致");
        }
        tweetReq.setUserName(user.getUserName());
        tweetDao.insertTweet(tweetReq);
    }

    /**
     * 所有用户的所有帖子
     *
     * @return {@link TweetListVo}
     * @author luojiayu
     * @date 2020/1/9
     */
    public TweetListVo listAllTweet() {
        List<Tweet> listTweet = tweetDao.getListTweet(null);
        List<Tweet> tweets = new ArrayList<>();
        for (Tweet t : listTweet) {
            Tweet tweet = new Tweet();
            tweet.setContent(t.getContent());
            tweet.setTitle(t.getTitle());
            tweet.setTumbupsList(t.getTumbupsList());
            tweet.setTweetId(t.getTweetId());
            tweet.setTweetUserId(t.getTweetUserId());
            tweet.setTweetUserName(userDao.getUserById(t.getTweetUserId()).getUserName());
            tweet.setNum(t.getTumbupsList().size());
            tweet.setReleaseTime(t.getReleaseTime());
            tweets.add(tweet);
        }
        log.info("全部帖子信息：{}", tweets);
        return TweetListVo.builder()
                .tweets(tweets)
                .build();
    }

    /**
     * 当前用户的所有帖子
     *
     * @param userId
     * @return
     */
    public TweetListVo listAllUserTweet(Integer userId) {
        User user = UserUtils.getUserInfo();
        List<Tweet> list = new ArrayList<>();
        if (!userId.equals(user.getId())) {
            throw new AppException("当前的用户与查询帖子用户不匹配");
        }
        List<Tweet> listTweets = tweetDao.getListTweet(userId);
        if (!CollectionUtils.isEmpty(listTweets)) {
            for (Tweet t : listTweets) {
                Tweet tweet = new Tweet();
                tweet.setContent(t.getContent());
                tweet.setTitle(t.getTitle());
                tweet.setTumbupsList(t.getTumbupsList());
                tweet.setTweetId(t.getTweetId());
                tweet.setTweetUserId(t.getTweetUserId());
                tweet.setTweetUserName(userDao.getUserById(t.getTweetUserId()).getUserName());
                tweet.setNum(t.getTumbupsList().size());
                tweet.setReleaseTime(t.getReleaseTime());
                list.add(tweet);
            }
        }
        log.info("当前用户信息为：，{}，帖子信息为：{}", user, list);
        return TweetListVo.builder()
                .tweets(list)
                .build();
    }

    /**
     * 发送评论
     *
     * @param commentReq 评论请求
     * @author luojiayu
     * @date 2020/1/9
     */
    @Transactional
    public void sendContent(CommentReq commentReq) {
        log.info("入参：{}", commentReq);
        User user = UserUtils.getUserInfo();
        if (!commentReq.getUserId().equals(user.getId())) {
            throw new AppException("当前用户id不一致无法进行帖子的评论");
        }
        //查询帖子是否存在
        Tweet tweet = tweetDao.getTweetByIds(commentReq.getTweetId());
        if (null == tweet) {
            throw new AppException("帖子不存在");
        }
        //评论者
        User user1 = userDao.getUserById(commentReq.getUserId());
        //被评论者
        User user2 = userDao.getUserById(commentReq.getUserBid());
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentReq, comment);
        comment.setUserName(user1.getUserName());
        comment.setUserBName(user2.getUserName());
        log.info("当前插入的评论信息为：{}", comment);
        commentDao.insertComment(comment);
    }

    /**
     * 点赞和取消点赞
     *
     * @param tweetId 帖子id
     * @param userId  点赞用户id
     * @author luojiayu
     * @date 2020/1/10
     */
    @Transactional
    public String clickTumbups(Integer tweetId, Integer userId) {
        log.info("tweetId:{},userId:{}", tweetId, userId);
        User user = UserUtils.getUserInfo();
        if (null == user) {
            throw new AppException("请登录");
        }
        if (!userId.equals(user.getId())) {
            throw new AppException("用户登录不一致，无法进行点赞");
        }
        Tweet tweet = tweetDao.getTweetByIds(tweetId);
        if (null == tweet) {
            throw new AppException("帖子不存在无法进行点赞");
        }
        //查询点赞记录是否存在
        Tumbups tumbups_db = tumbupsDao.getTumbupsById(tweetId, userId);
        //查询数据为空，进行插入
        if (null == tumbups_db) {
            int result = tumbupsDao.inseretTumbupsById(tweetId, userId, user.getUserName());
            if (result > 0) {
                return "点赞成功";
            } else {
                return "点赞失败";
            }
        }
        //未点赞，进行点赞
        if (TumupusEnum.TUMUPUSNOT.getCode() == tumbups_db.getStatusFlag()) {
            int result = tumbupsDao.notToTumbups(tweetId, userId);
            if (result > 0) {
                return "点赞成功";
            } else {
                return "点赞失败";
            }
        }
        //已点赞，进行取消点赞
        if (TumupusEnum.TUMUPUS.getCode() == tumbups_db.getStatusFlag()) {
            int result = tumbupsDao.tumbupsToNot(tweetId, userId);
            if (result > 0) {
                return "取消点赞成功";
            } else {
                return "取消点赞失败";
            }
        }
        return null;
    }

    /**
     * 发送回复
     *
     * @param req
     */
    public void sendReply(ReplyReq req) {
        //先查询这条帖子有没有评论
        Comment comment = commentDao.selectByIds(req.getTweetId(), req.getCommentId());
        if (null == comment) {
            throw new AppException("该帖子没有任何评论无法进行回复");
        }
        //回复者信息
        User user1 = userDao.getUserById(req.getUserId());
        //被回复者信息
        User user2 = userDao.getUserById(req.getUserBid());
        req.setUserName(user1.getUserName());
        req.setUserBName(user2.getUserName());
        replyDao.insertReply(req);
    }

    /**
     * 获取帖子详情信息
     *
     * @param tweetId 帖子id
     * @author luojiayu
     * @date 2020/1/13
     */
    public TweetDetailsVo getTweetDetails(Integer tweetId) {
        log.info("帖子id为：{}", tweetId);
        //通过帖子id查询帖子是否存在
        Tweet tweet_db = tweetDao.getTweetByIds(tweetId);
        if (null == tweet_db) {
            throw new AppException("没有id为" + tweetId + "的帖子");
        }
        TweetDetailsVo tweetDetailsVo = tweetDao.getTweetDetailsById(tweetId);
        tweetDetailsVo.setNum(tweetDetailsVo.getTumbupsList().size());
        log.info("查询帖子详情信息：{}", tweetDetailsVo);
        return tweetDetailsVo;
    }

    /**
     * 修改帖子信息
     *
     * @param req
     */
    public void modifiedTweet(TweetModifiedReq req) {
        Tweet tweet_db = tweetDao.getTweetByIds(req.getId());
        if (null == tweet_db) {
            throw new AppException("不存在id为" + req.getId() + "的帖子");
        }
        int reslut = tweetDao.modifiedTweet(req);
        if (reslut <= 0) {
            throw new AppException("修改帖子信息失败");
        }
    }

    /**
     * 删除帖子信息
     *
     * @param tweetId
     * @param userId
     */
    public void delTweet(Integer tweetId, Integer userId) {
        User user = UserUtils.getUserInfo();
        if (!userId.equals(user.getId())) {
            throw new AppException("当前用户不一致，无法进行帖子的删除");
        }
        Tweet tweet_db = tweetDao.getTweetById(tweetId, userId);
        if (null == tweet_db) {
            throw new AppException("该用户不存在id为：" + tweetId + "的帖子");
        }
        int result = tweetDao.delTweet(tweetId, userId);
        if (result <= 0) {
            throw new AppException("删除帖子失败");
        }
    }

    /**
     * 删除评论
     *
     * @param tweetId   帖子id
     * @param commentId 评论id
     * @param userId    评论者id
     */
    public void delComment(Integer tweetId, Integer commentId, Integer userId) {
        User user = UserUtils.getUserInfo();
        if (userId != user.getId()) {
            throw new AppException("用户不一致无法删除评论信息");
        }
        //查询这条评论是否存在
        Comment comment = commentDao.selectByIds(tweetId, commentId);
        if (comment == null) {
            throw new AppException("该条评论不存在");
        }
        int result = commentDao.delComment(tweetId, commentId, userId);
        if (result <= 0) {
            throw new AppException("删除评论失败");
        }
    }

    /**
     * 删除回复
     *
     * @param tweetId   帖子id
     * @param commentId 评论id
     * @param replyId   回复id
     * @param userId    当前用户id
     */
    public void delReply(Integer tweetId,
                         Integer commentId,
                         Integer replyId,
                         Integer userId) {
        User user = UserUtils.getUserInfo();
        if (userId != user.getId()) {
            throw new AppException("用户不一致无法删除回复信息");
        }
        int result = replyDao.delReply(tweetId, commentId, replyId, userId);
        if (result <= 0) {
            throw new AppException("删除回复失败");
        }
    }
}
