package top.yoga.lol.tweet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luojiayu
 * @date 2020/1/13 16:02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

    /**
     * 回复表id
     */
    private Integer id;

    /**
     * 回复表父id
     */
    private Integer pid;

    /**
     * 帖子id
     */
    private Integer tweetId;

    /**
     * 评论id
     */
    private Integer commentId;

    /**
     * 回复者id
     */
    private Integer userId;

    /**
     * 回复者名字
     */
    private String replyName;

    /**
     * 被回复者id
     */
    private Integer userBid;

    /**
     * 被回复者名字
     */
    private String replyBName;

    /**
     * 回复内容
     */
    private String content;
}
