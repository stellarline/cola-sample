package net.futureorigin.architecture.sample.cola.dto.command.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import net.futureorigin.architecture.sample.cola.dto.command.CommonCommand;

import javax.validation.constraints.NotNull;

/**
 * UserStateUpdateCmd
 * <p></p>
 *
 * @author Leander Lee on 2021/9/4.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserStateUpdateCmd extends CommonCommand {

    @NotNull(message = "ID不可为空。")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @NotNull(message = "状态不可为空。")
    private Integer state;

}
