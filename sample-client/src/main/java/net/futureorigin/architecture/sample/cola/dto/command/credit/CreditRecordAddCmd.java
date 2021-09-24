package net.futureorigin.architecture.sample.cola.dto.command.credit;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.futureorigin.architecture.sample.cola.dto.command.CommonCommand;
import lombok.*;

/**
 * CreditRecordAddCmd
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreditRecordAddCmd extends CommonCommand {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    private Integer type;

    private Double credit;
}
