package top.yoga.lol.tweet.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 评论请求
 *
 * @author luojiayu
 * @date 2020/1/9 15:59
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentReq {

    /**
     * 帖子id
     */
    @NotNull(message = "帖子id不能为空")
    private Integer tweetId;

    /**
     * 评论者id
     */
    @NotNull(message = "评论者id不能为空")
    private Integer userId;

    /**
     * 被评论者id
     */
    @NotNull(message = "被评论者id不能为空")
    private Integer userBid;

    /**
     * 用户评论
     */
    @NotNull(message = "评论不能为空")
    private String content;
}
