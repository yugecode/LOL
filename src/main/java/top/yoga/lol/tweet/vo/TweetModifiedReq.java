package top.yoga.lol.tweet.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author luojiayu
 * @date 2020/1/14 18:10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TweetModifiedReq {

    /**
     * 帖子id
     */
    @NotNull(message = "帖子id不能为空")
    private Integer id;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Integer userId;

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
