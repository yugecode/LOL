package top.yoga.lol.tweet.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.yoga.lol.tweet.entity.Tweet;

import java.util.List;

/**
 * 帖子列表
 *
 * @author luojiayu
 * @date 2020/1/9 10:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TweetListVo {

    private List<Tweet> tweets;
}
