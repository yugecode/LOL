package top.yoga.lol.user.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luojiayu
 * @date 2020/1/7 14:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    /**
     * 用户id
     */
    private int id;

    /**
     * 用户账号
     */
    private String userName;
    /**
     * 用户密码
     */
    @JsonIgnore
    private String password;

    /**
     * 用户邮箱
     */
    private String email;
}
