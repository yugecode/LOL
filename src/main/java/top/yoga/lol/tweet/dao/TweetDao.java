package top.yoga.lol.tweet.dao;

import org.apache.ibatis.annotations.Mapper;
import top.yoga.lol.tweet.entity.Tweet;

/**
 * 帖子持久层
 *
 * @author luojiayu
 * @date 2020/1/8
 */
@Mapper
public interface TweetDao {

    int insertTweet(Tweet tweet);
}
