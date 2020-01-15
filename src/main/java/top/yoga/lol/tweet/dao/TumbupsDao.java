package top.yoga.lol.tweet.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.yoga.lol.tweet.entity.Tumbups;

import java.util.List;

/**
 * 点赞持久层
 *
 * @author luojiayu
 * @date 2020/1/10 14:41
 */
@Mapper
public interface TumbupsDao {

    /**
     * 通过帖子id和用户id查询点赞
     *
     * @param tweetId 帖子id
     * @param userId  点赞用户id
     * @return
     */
    Tumbups getTumbupsById(@Param("tweetId") Integer tweetId,
                           @Param("userId") Integer userId);

    /**
     * 通过帖子id查询该帖子所有点赞
     *
     * @param tweetId
     * @return
     */
    List<Tumbups> getTumbupsListById(@Param("tweetId") Integer tweetId);

    /**
     * 插入点赞信息
     *
     * @param tweetId 帖子id
     * @param userId  用户id
     * @return
     */
    int inseretTumbupsById(@Param("tweetId") Integer tweetId,
                           @Param("userId") Integer userId,
                           @Param("userName")String userName);

    /**
     * 点赞到非点赞
     *
     * @param tweetId 帖子id
     * @param userId  用户id
     * @return
     */
    int tumbupsToNot(@Param("tweetId") Integer tweetId,
                     @Param("userId") Integer userId);

    /**
     * 非点赞到点赞
     *
     * @param tweetId 帖子id
     * @param userId  用户id
     * @return
     */
    int notToTumbups(@Param("tweetId") Integer tweetId,
                     @Param("userId") Integer userId);

}
