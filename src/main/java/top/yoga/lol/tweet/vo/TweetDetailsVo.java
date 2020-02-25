package top.yoga.lol.tweet.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.yoga.lol.tweet.entity.CommentReply;
import top.yoga.lol.tweet.entity.Tumbups;

import java.util.Date;
import java.util.List;

/**
 * 帖子详情
 *
 * @author luojiayu
 * @date 2020/1/13 10:52
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TweetDetailsVo {

    /**
     * 帖子id
     */
    private Integer tweetId;

    /**
     * 帖子标题
     */
    private String tweetTitle;

    /**
     * 帖子内容
     */
    private String tweetContent;

    /**
     * 发帖人id
     */
    private Integer tweetUserId;

    /**
     * 发帖人姓名
     */
    private String userName;

    /**
     * 评论及其回复
     */
    private List<CommentReply> commentReplies;

    /**
     * 点赞人列表
     */
    private List<Tumbups> tumbupsList;

    /**
     * 帖子发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm", timezone = "GMT+8")
    private Date releaseTime;

    /**
     * 点赞数量
     */
    private Integer num;
}
