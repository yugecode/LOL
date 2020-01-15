package top.yoga.lol.tweet.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.yoga.lol.tweet.entity.Comment;

import java.util.List;

/**
 * 评论表持久层方法
 *
 * @author luojiayu
 * @date 2020/1/9 16:07
 */
@Mapper
public interface CommentDao {

    int insertComment(Comment comment);

    Comment selectByIds(@Param("tweetId") Integer tweetId,
                        @Param("commentId") Integer commentId);

    List<Integer> selectCommentId(Integer tweetId);

    int delComment(@Param("tweetId") Integer tweetId,
                   @Param("commentId") Integer commentId,
                   @Param("userId") Integer userId);
}
