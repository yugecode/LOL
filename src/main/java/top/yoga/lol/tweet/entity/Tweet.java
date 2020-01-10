package top.yoga.lol.tweet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    /**
     * 帖子id
     */
    private Integer tweetId;

    /**
     * 帖子标题
     */
    private String title;

    /**
     * 帖子内容
     */
    private String content;

    /**
     * 发帖用户id
     */
    private Integer tweetUserId;

    /**
     * 发帖用户名
     */
    private String tweetUserName;

    /**
     * 点赞信息
     */
    private List<Tumbups> tumbupsList;

    /**
     * 点赞数量
     */
    private Integer num;

    /**
     * 帖子发布时间
     */
    private String releaseTime;


}
