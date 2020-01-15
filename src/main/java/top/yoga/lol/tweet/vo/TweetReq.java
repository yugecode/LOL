package top.yoga.lol.tweet.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 发帖请求
 *
 * @author luojiayu
 * @date 2020/1/8 17:52
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TweetReq {

    /**
     * 帖子id
     */
    @JsonIgnore
    private Integer tweetId;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    /**
     * 用户名
     */
    @JsonIgnore
    private String userName;

    /**
     * 发帖标题
     */
    @NotBlank(message = "帖子标题不能为空")
    private String title;

    /**
     * 发帖内容
     */
    @NotBlank(message = "帖子内容不能为空")
    private String content;
}
