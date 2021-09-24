package net.futureorigin.architecture.sample.cola.dto.command.credit;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.futureorigin.architecture.sample.cola.dto.command.CommonCommand;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * CreditAddCmd
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreditAddCmd extends CommonCommand {

    @NotNull(message = "user id must be not null")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    @NotNull(message = "recharge credit must be not null")
    private Double credit;
}
