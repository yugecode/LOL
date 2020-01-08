package top.yoga.lol.user.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.yoga.lol.common.validation.PasswordPattern;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 忘记密码请求体
 *
 * @author luojiayu
 * @date 2020/1/8 18:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForgetPasswordReq {

    /**
     * 用户名
     */
    @NotBlank(message = "账户名不能为空")
    private String userName;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email
    private String email;

    /**
     * 邮箱验证码
     */
    @NotNull(message = "验证码不能为空")
    private Integer code;

    /**
     * 新密码
     */
    @PasswordPattern
    private String newPassword;

    /**
     * 密码确认
     */
    @PasswordPattern
    private String confirmPassword;

}
