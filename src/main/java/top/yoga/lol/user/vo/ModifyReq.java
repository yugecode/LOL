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
 * 修改密码请求
 *
 * @author luojiayu
 * @date 2019/12/27 15:05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModifyReq {

    /**
     * 修改后的密码
     */
    @NotBlank(message = "修改密码不能为空")
    @PasswordPattern
    private String modifyPassword;


}
