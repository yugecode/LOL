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
public class LoginReq2 {

    /**
     * 验证码
     */
    @NotNull(message = "验证码不能为空")
    private Integer code;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;
}
