package top.yoga.lol.tweet.dao;

import org.apache.ibatis.annotations.Mapper;
import top.yoga.lol.tweet.entity.Comment;

/**
 * 评论表持久层方法
 *
 * @author luojiayu
 * @date 2020/1/9 16:07
 */
@Mapper
public interface CommentDao {
    /**
     * 插入评论
     *
     * @param comment
     * @return {@link int}
     * @author luojiayu
     * @date 2020/1/9
     */
    int insertComment(Comment comment);
}
