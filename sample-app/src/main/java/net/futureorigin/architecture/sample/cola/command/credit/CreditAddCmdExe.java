package net.futureorigin.architecture.sample.cola.command.credit;

import net.futureorigin.architecture.sample.cola.common.exception.BizExceptionCode;
import net.futureorigin.architecture.sample.cola.common.exception.ObjectNotFoundException;
import net.futureorigin.architecture.sample.cola.dto.command.credit.CreditAddCmd;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.credit.CreditDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper.CreditMapper;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.repository.CreditRepository;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * CreditAddCmdExe
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@Component
public class CreditAddCmdExe {

    private final CreditRepository creditRepository;
    private final CreditMapper creditMapper;

    private final UserRepository userRepository;

    public CreditAddCmdExe(CreditRepository creditRepository,
                           CreditMapper creditMapper,
                           UserRepository userRepository) {
        this.creditRepository = creditRepository;
        this.creditMapper = creditMapper;
        this.userRepository = userRepository;
    }

    public void execute(CreditAddCmd cmd) {
        checkUserValid(cmd.getUserId());

        CreditDO creditDO = creditMapper.findByUserId(cmd.getUserId());
        if (null == creditDO) {
            creditDO = new CreditDO();
            creditDO.setCreator(cmd.getUserContext().getLoggedInUserId());
            creditDO.setCreationTime(new Date());
            creditDO.setUserId(cmd.getUserId());
            creditDO.setBalance(cmd.getCredit());
        } else {
            creditDO.setBalance(creditDO.getBalance() + cmd.getCredit());
        }
        creditDO.setModifier(cmd.getUserContext().getLoggedInUserId());
        creditDO.setModifiedTime(new Date());

        creditRepository.save(creditDO).getId();
    }

    private void checkUserValid(Long id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new ObjectNotFoundException(
                    BizExceptionCode.USER_NOT_FOUND.getCode(),
                    BizExceptionCode.USER_NOT_FOUND.getMessage()
            );
        }
    }
}
