package top.yoga.lol.tweet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 点赞实体类
 *
 * @author luojiayu
 * @date 2020/1/9 10:44
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class tumbups {

    /**
     * 点赞id
     */
    private Integer tumbupsId;

    /**
     * 帖子id
     */
    private Integer tweetId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;
}
