package net.futureorigin.architecture.sample.cola.dto.command.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import net.futureorigin.architecture.sample.cola.dto.command.CommonCommand;

import javax.validation.constraints.NotNull;

/**
 * UserUpdateCmd
 * <p></p>
 *
 * @author Leander Lee on 2021/9/3.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateCmd extends CommonCommand {

    @NotNull(message = "ID不可为空。")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    private String name;

    private String mobile;

}
