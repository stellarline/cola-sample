package net.futureorigin.architecture.sample.cola.dto.command.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import net.futureorigin.architecture.sample.cola.dto.command.CommonCommand;

import javax.validation.constraints.NotNull;

/**
 * UserGetQryCmd
 * <p></p>
 *
 * @author Leander Lee on 2021/9/4.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserGetQry extends CommonCommand {

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    private String mobile;
}
