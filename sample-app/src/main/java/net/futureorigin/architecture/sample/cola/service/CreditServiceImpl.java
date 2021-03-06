package net.futureorigin.architecture.sample.cola.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import net.futureorigin.architecture.sample.cola.api.CreditService;
import net.futureorigin.architecture.sample.cola.api.UserService;
import net.futureorigin.architecture.sample.cola.command.credit.CreditAddCmdExe;
import net.futureorigin.architecture.sample.cola.command.credit.CreditDeductionCmdExe;
import net.futureorigin.architecture.sample.cola.command.credit.CreditRecordAddCmdExe;
import net.futureorigin.architecture.sample.cola.context.UserContext;
import net.futureorigin.architecture.sample.cola.domain.model.credit.CreditRecord;
import net.futureorigin.architecture.sample.cola.dto.command.credit.CreditAddCmd;
import net.futureorigin.architecture.sample.cola.dto.command.credit.CreditDeductionCmd;
import net.futureorigin.architecture.sample.cola.dto.command.credit.CreditRecordAddCmd;
import net.futureorigin.architecture.sample.cola.dto.command.user.UserGradeRefreshCmd;
import net.futureorigin.architecture.sample.cola.support.TransactionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CreditServiceImpl
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@Service
@CatchAndLog
@Slf4j
public class CreditServiceImpl extends BaseService implements CreditService {

    private final CreditAddCmdExe creditAddCmdExe;
    private final CreditRecordAddCmdExe creditRecordAddCmdExe;
    private final CreditDeductionCmdExe creditDeductionCmdExe;

    @Autowired
    @Lazy
    private UserService userService;

    public CreditServiceImpl(CreditAddCmdExe creditAddCmdExe,
                             CreditRecordAddCmdExe creditRecordAddCmdExe,
                             CreditDeductionCmdExe creditDeductionCmdExe) {
        this.creditAddCmdExe = creditAddCmdExe;
        this.creditRecordAddCmdExe = creditRecordAddCmdExe;
        this.creditDeductionCmdExe = creditDeductionCmdExe;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response add(CreditAddCmd cmd) {
        try {
            // ????????????
            creditAddCmdExe.execute(cmd);

            // ????????????????????????
            CreditRecordAddCmd creditRecordAddCmd = new CreditRecordAddCmd(
                    cmd.getUserId(), CreditRecord.TYPE_ADD, cmd.getCredit()
            );
            creditRecordAddCmd.setUserContext(cmd.getUserContext());
            creditRecordAddCmdExe.execute(creditRecordAddCmd);

            // ??????????????????
            refreshUserGrade(cmd.getUserContext(), cmd.getUserId());

            return Response.buildSuccess();
        } catch (Exception e) {
            TransactionSupport.setRollback();
            log.error("?????????????????? : ", e);
            return getErrorResponse(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response deduction(CreditDeductionCmd cmd) {
        try {
            double deductionCredit = creditDeductionCmdExe.execute(cmd);
            // ??????????????????
            CreditRecordAddCmd creditRecordAddCmd = new CreditRecordAddCmd(
                    cmd.getUserId(), CreditRecord.TYPE_CONSUME, deductionCredit
            );
            creditRecordAddCmd.setUserContext(cmd.getUserContext());
            creditRecordAddCmdExe.execute(creditRecordAddCmd);

            // ????????????
            refreshUserGrade(cmd.getUserContext(), cmd.getUserId());

            return Response.buildSuccess();
        } catch (Exception e) {
            TransactionSupport.setRollback();
            log.error("?????????????????? : ", e);
            return getErrorResponse(e);
        }
    }

    private void refreshUserGrade(UserContext userContext, Long userId) {
        UserGradeRefreshCmd gradeRefreshCmd = new UserGradeRefreshCmd(userId);
        gradeRefreshCmd.setUserContext(userContext);
        Response gradeRefreshResp = userService.refreshGrade(gradeRefreshCmd);
        if (!gradeRefreshResp.isSuccess()) {
            throw new BizException(gradeRefreshResp.getErrCode(), gradeRefreshResp.getErrMessage());
        }
    }
}
