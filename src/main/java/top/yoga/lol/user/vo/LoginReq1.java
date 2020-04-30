package top.yoga.lol.user.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 登录请求
 *
 * @author luojiayu
 * @date 2020/1/7 10:29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginReq1 {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
}
