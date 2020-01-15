package top.yoga.lol.tweet.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.yoga.lol.tweet.entity.Reply;
import top.yoga.lol.tweet.vo.ReplyReq;

import java.util.List;

/**
 * @author luojiayu
 * @date 2020/1/13 15:52
 */
@Mapper
public interface ReplyDao {

    /**
     * 通过相关信息查询回复信息
     *
     * @param tweetId   帖子id
     * @param commentId 评论id
     * @param userBid   被回复者id
     * @return
     */
    List<Reply> selectReply(@Param("tweetId") Integer tweetId,
                            @Param("commentId") Integer commentId,
                            @Param("userBid") Integer userBid);

    int insertReply(ReplyReq req);

    int delReply(@Param("tweetId") Integer tweetId,
                 @Param("commentId") Integer commentId,
                 @Param("replyId") Integer replyId,
                 @Param("userId") Integer userId);
}
