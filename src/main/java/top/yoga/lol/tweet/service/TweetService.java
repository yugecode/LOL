package top.yoga.lol.tweet.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.yoga.lol.common.exception.AppException;
import top.yoga.lol.common.page.PageQueryBean;
import top.yoga.lol.tweet.dao.CommentDao;
import top.yoga.lol.tweet.dao.TweetDao;
import top.yoga.lol.tweet.entity.Comment;
import top.yoga.lol.tweet.entity.Tweet;
import top.yoga.lol.tweet.vo.CommentReq;
import top.yoga.lol.tweet.vo.TweetListVo;
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
        Tweet tweet = new Tweet();
        BeanUtils.copyProperties(tweetReq, tweet);
        tweetDao.insertTweet(tweet);
    }

    /**
     * 所有用户的所有帖子
     *
     * @return {@link TweetListVo}
     * @author luojiayu
     * @date 2020/1/9
     */
    public TweetListVo listAllTweet() {
        PageQueryBean<TweetListVo> page = new PageQueryBean<>();
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

    @Transactional
    public void sendContent(CommentReq commentReq) {
        log.info("入参：{}", commentReq);
        User user = UserUtils.getUserInfo();
        if (!commentReq.getUserId().equals(user.getId())) {
            throw new AppException("当前用户id不一致无法进行帖子的评论");
        }
        //查询帖子是否存在
        Tweet tweet = tweetDao.getTweetById(commentReq.getTweetId());
        if (null == tweet) {
            throw new AppException("帖子不存在");
        }
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentReq, comment);
        log.info("当前插入的评论信息为：{}", comment);
        commentDao.insertComment(comment);
    }
}
