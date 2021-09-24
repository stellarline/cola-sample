package net.futureorigin.architecture.sample.cola.dto.command.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.futureorigin.architecture.sample.cola.dto.command.CommonCommand;
import lombok.*;

/**
 * UserGradeRefreshCmd
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserGradeRefreshCmd extends CommonCommand {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
}
