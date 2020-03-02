package top.yoga.lol.tweet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 消息表
 *
 * @author luojiayu
 * @date 2020/3/2 10:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    /**
     * 消息id
     */
    private Integer id;

    /**
     * 发送消息人的id
     */
    private Integer userId;

    /**
     * 发送消息人的名称
     */
    private String userName;

    /**
     * 接收消息的id
     */
    private Integer toUserId;

    /**
     * 接收消息的用户名
     */
    private String toUserName;

    /**
     * 帖子id
     */
    private Integer tweetId;

    /**
     * 评论id
     */
    private Integer commentId;

    /**
     * 回复id
     */
    private Integer replyId;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 1.已读 0.未读（默认为0）
     */
    private Integer state;

    /**
     * 消息创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm", timezone = "GMT+8")
    private Date msgCreateTime;

    /**
     * 消息修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm", timezone = "GMT+8")
    private Date msgModifiedTime;
}
