package top.yoga.lol.tweet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评论表实体
 *
 * @author luojiayu
 * @date 2020/1/9 16:05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    /**
     * 评论id
     */
    private Integer commentId;

    /**
     * 帖子id
     */
    private Integer tweetId;

    /**
     * 评论者id
     */
    private Integer userId;

    /**
     * 评论者名字
     */
    private String userName;

    /**
     * 被评论者id
     */
    private Integer userBid;

    /**
     * 被评论者名字
     */
    private String userBName;

    /**
     * 用户评论
     */
    private String content;
}
