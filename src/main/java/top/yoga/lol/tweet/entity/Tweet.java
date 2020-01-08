package top.yoga.lol.tweet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 帖子实体类
 *
 * @author luojiayu
 * @date 2020/1/8 21:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {

    private Integer id;

    private String title;

    private String content;

    private Integer userId;
}
