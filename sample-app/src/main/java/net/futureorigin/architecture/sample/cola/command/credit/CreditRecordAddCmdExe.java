package net.futureorigin.architecture.sample.cola.command.credit;

import net.futureorigin.architecture.sample.cola.context.UserContext;
import net.futureorigin.architecture.sample.cola.dto.command.credit.CreditRecordAddCmd;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.credit.CreditRecordDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.repository.CreditRecordRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * CreditRecordAddCmdExe
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@Component
public class CreditRecordAddCmdExe {

    private final CreditRecordRepository recordRepository;

    public CreditRecordAddCmdExe(CreditRecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public void execute(CreditRecordAddCmd cmd) {
        CreditRecordDO recordDO = new CreditRecordDO();
        recordDO.setUserId(cmd.getUserId());
        recordDO.setType(cmd.getType());
        recordDO.setCredit(cmd.getCredit());
        UserContext context = cmd.getUserContext();
        recordDO.setCreator(context.getLoggedInUserId());
        recordDO.setModifier(context.getLoggedInUserId());
        recordDO.setCreationTime(new Date());
        recordDO.setModifiedTime(new Date());

        recordRepository.save(recordDO);
    }
}
