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
     * 用户id
     */
    private Integer userId;

    /**
     * 用户评论
     */
    private String content;
}
