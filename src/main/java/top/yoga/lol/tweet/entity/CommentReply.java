package top.yoga.lol.tweet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author luojiayu
 * @date 2020/1/14 14:01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentReply {

    /**
     * 评论id
     */
    private Integer commentId;

    /**
     * 评论者id，发布评论的人id
     */
    private Integer commentUserId;

    /**
     * 发布评论的用户名
     */
    private String commentUserName;

    /**
     * 被评论者的id
     */
    private Integer commentUserBid;

    /**
     * 被评论者名字
     */
    private String commentUserBName;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论下的回复
     */
    private List<Reply> replyList;
}
