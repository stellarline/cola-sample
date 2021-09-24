package net.futureorigin.architecture.sample.cola.dto.command.credit;

import net.futureorigin.architecture.sample.cola.dto.command.CommonCommand;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * CreditDeductionCmd
 * <p></p>
 *
 * @author Leander Lee create on 2021/9/15.
 */
@Getter
@Setter
@ToString
public class CreditDeductionCmd extends CommonCommand {

    @NotNull
    private Long userId;
}
