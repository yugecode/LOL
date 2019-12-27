package top.yoga.lol.user.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

/**
 * 登录请求
 *
 * @author luojiayu
 * @date 2019/12/27 15:05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterReq {

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    @Email
    private String email;

    /**
     * 验证码
     */
    private Integer code;
}
