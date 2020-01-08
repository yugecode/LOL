package top.yoga.lol.tweet.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yoga.lol.common.exception.AppException;
import top.yoga.lol.tweet.dao.TweetDao;
import top.yoga.lol.tweet.entity.Tweet;
import top.yoga.lol.tweet.vo.TweetReq;
import top.yoga.lol.user.entity.User;
import top.yoga.lol.user.utils.UserUtils;

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
}
