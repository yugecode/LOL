package top.yoga.lol.user.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.yoga.lol.common.validation.PasswordPattern;
import top.yoga.lol.common.validation.UsernamePattern;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotBlank(message = "用户名不能为空")
    @UsernamePattern
    private String userName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @PasswordPattern
    private String password;

    /**
     * 确认密码
     */
    @NotBlank(message = "确认密码不能为空")
    @PasswordPattern
    private String re_password;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email
    private String email;

    /**
     * 验证码
     */
    @NotNull(message = "验证码不能为空")
    private Integer code;
}
