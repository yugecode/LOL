package top.yoga.lol.tweet.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.yoga.lol.tweet.entity.Tweet;
import top.yoga.lol.tweet.vo.TweetDetailsVo;
import top.yoga.lol.tweet.vo.TweetModifiedReq;
import top.yoga.lol.tweet.vo.TweetReq;

import java.util.List;

/**
 * 帖子持久层
 *
 * @author luojiayu
 * @date 2020/1/8
 */
@Mapper
public interface TweetDao {

    int insertTweet(TweetReq tweet);

    List<Tweet> getListTweet(@Param("userId") Integer userId);

    Tweet getTweetByIds(@Param("tweetId") Integer tweetId);

    Tweet getTweetById(@Param("tweetId") Integer tweetId,
                       @Param("userId") Integer userId);

    int modifiedTweet(TweetModifiedReq tweetModifiedReq);

    int delTweet(@Param("tweetId") Integer tweetId,
                 @Param("userId") Integer userId);

    TweetDetailsVo getTweetDetailsById(Integer tweetId);
}
