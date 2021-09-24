package net.futureorigin.architecture.sample.cola.api;

import com.alibaba.cola.dto.Response;
import net.futureorigin.architecture.sample.cola.dto.command.credit.CreditAddCmd;
import net.futureorigin.architecture.sample.cola.dto.command.credit.CreditDeductionCmd;

/**
 * CreditService
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
public interface CreditService {

    Response add(CreditAddCmd cmd);

    Response deduction(CreditDeductionCmd cmd);

}
