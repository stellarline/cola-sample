package net.futureorigin.architecture.sample.cola.dto.command.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.futureorigin.architecture.sample.cola.dto.command.CommonCommand;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * UserPasswordResetCmd
 * <p></p>
 *
 * @author Leander Lee on 2021/9/4.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordResetCmd extends CommonCommand {

    @NotNull(message = "ID不可为空。")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @NotBlank(message = "密码不可为空。")
    @Size(min = 6, max = 20, message = "密码必须大于或等于6位。")
    private String password;
}
