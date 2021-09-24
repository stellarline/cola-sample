package net.futureorigin.architecture.sample.cola.command.credit;

import com.alibaba.cola.exception.BizException;
import net.futureorigin.architecture.sample.cola.common.exception.BizExceptionCode;
import net.futureorigin.architecture.sample.cola.common.exception.ObjectNotFoundException;
import net.futureorigin.architecture.sample.cola.dto.command.credit.CreditDeductionCmd;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.credit.CreditDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.gradecreditrule.GradeCreditRuleDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.user.UserDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper.CreditMapper;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper.GradeCreditRuleMapper;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper.UserMapper;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.repository.CreditRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * CreditDeductionCmdExe
 * <p></p>
 *
 * @author Leander Lee create on 2021/9/15.
 */
@Component
public class CreditDeductionCmdExe {

    private final UserMapper userMapper;
    private final GradeCreditRuleMapper creditRuleMapper;
    private final CreditMapper creditMapper;
    private final CreditRepository creditRepository;

    public CreditDeductionCmdExe(UserMapper userMapper,
                                 GradeCreditRuleMapper creditRuleMapper,
                                 CreditMapper creditMapper,
                                 CreditRepository creditRepository) {
        this.creditRuleMapper = creditRuleMapper;
        this.userMapper = userMapper;
        this.creditMapper = creditMapper;
        this.creditRepository = creditRepository;
    }

    public double execute(CreditDeductionCmd cmd) {
        UserDO userDO = userMapper.findById(cmd.getUserId());
        if (null == userDO) {
            throw new ObjectNotFoundException(
                    BizExceptionCode.USER_NOT_FOUND.getCode(),
                    BizExceptionCode.USER_NOT_FOUND.getMessage()
            );
        }
        // 查找对应的积分规则根据 gradeCode
        GradeCreditRuleDO gradeCreditRuleDO = creditRuleMapper.findByGradeCode(userDO.getGradeCode());
        double deductionCredit = gradeCreditRuleDO.getCreditDeductQuota();
        CreditDO creditDO = creditMapper.findByUserId(cmd.getUserId());
        if (creditDO.getBalance() < deductionCredit) {
            throw new BizException(
                    BizExceptionCode.CREDIT_NOT_ENOUGH.getCode(),
                    BizExceptionCode.CREDIT_NOT_ENOUGH.getMessage()
            );
        }

        double latestBalance = BigDecimal.valueOf(creditDO.getBalance())
                .subtract(BigDecimal.valueOf(deductionCredit))
                .doubleValue();
        creditDO.setBalance(latestBalance);
        creditRepository.save(creditDO);

        return deductionCredit;
    }
}
