package top.yoga.lol.tweet.dao;

import org.apache.ibatis.annotations.Mapper;
import top.yoga.lol.tweet.entity.Message;

import java.util.List;

/**
 * 消息
 *
 * @author luojiayu
 * @date 2020/3/2 10:39
 */

@Mapper
public interface MessageDao {

    /**
     * 通过用户id获取用户消息
     *
     * @param id
     * @return
     */
    List<Message> getMessageList(Integer id);

    /**
     * 通过消息id获取消息
     *
     * @param id
     * @return
     */
    Message getMessageById(Integer id);

    /**
     * 插入消息
     *
     * @param message
     * @return
     */
    int insertMessage(Message message);

    /**
     * 更新状态
     *
     * @param id
     * @return
     */
    int updateMessage(Integer id);

    /**
     * 删除消息
     *
     * @param id
     * @return
     */
    int delMessage(Integer id);
}
