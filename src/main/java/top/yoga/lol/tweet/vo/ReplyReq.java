package top.yoga.lol.tweet.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 回复请求
 *
 * @author luojiayu
 * @date 2020/1/13 10:48
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyReq {

    @JsonIgnore
    private Integer replyId;
    /**
     * 帖子id
     */
    @NotNull(message = "帖子id不能为空")
    private Integer tweetId;

    /**
     * 评论id
     */
    @NotNull(message = "评论id不能为空")
    private Integer commentId;

    /**
     * 回复用户id
     */
    @NotNull(message = "回复者id不能为空")
    private Integer userId;

    /**
     * 回复者名字
     */
    @JsonIgnore
    private String userName;

    /**
     * 被回复的用户id，指的是发表评论的用户id
     */
    @NotNull(message = "被回复者id不能为空")
    private Integer userBid;

    /**
     * 被回复者名字
     */
    @JsonIgnore
    private String userBName;
    /**
     * 回复内容
     */
    @NotBlank(message = "回复信息不能为空")
    private String content;

}
