package net.futureorigin.architecture.sample.cola.dto.command.user;

import net.futureorigin.architecture.sample.cola.dto.command.CommonCommand;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * UserAddCmd
 * <p></p>
 *
 * @author Leander Lee on 2021/9/1.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserAddCmd extends CommonCommand {

    @NotBlank(message = "手机号码不可为空。")
    @Size(min = 11, max = 11, message = "手机号码格式不正确。")
    private String mobile;

    @NotBlank(message = "密码不可为空。")
    @Size(min = 6, max = 20, message = "密码必须大于或等于6位。")
    private String password;
}
