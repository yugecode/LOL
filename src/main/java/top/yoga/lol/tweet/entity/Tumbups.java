package top.yoga.lol.tweet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Tumbups {

    /**
     * 点赞id
     */
    private Integer tumbupsId;

    /**
     * 用户id
     */
    private Integer tumbupsUserId;

    /**
     * 用户名
     */
    private String tumbupsUserName;

    /**
     * 点赞状态 1.点赞 0.未点赞
     */
    @JsonIgnore
    private Integer statusFlag;

}
