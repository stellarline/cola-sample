package net.futureorigin.architecture.sample.cola.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import lombok.extern.slf4j.Slf4j;
import net.futureorigin.architecture.sample.cola.api.GradeCreditRuleService;
import net.futureorigin.architecture.sample.cola.command.gradecreditrule.GradeCreditRuleAddCmdExe;
import net.futureorigin.architecture.sample.cola.command.gradecreditrule.GradeCreditRuleListQryExe;
import net.futureorigin.architecture.sample.cola.dto.clientobject.gradecreditrule.GradeCreditRuleListCO;
import net.futureorigin.architecture.sample.cola.dto.command.gradecreditrule.GradeCreditRuleAddCmd;
import net.futureorigin.architecture.sample.cola.dto.command.gradecreditrule.GradeCreditRuleListQry;
import net.futureorigin.architecture.sample.cola.support.TransactionSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * GradeCreditRuleServiceImpl
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@Service
@Slf4j
@CatchAndLog
public class GradeCreditRuleServiceImpl extends BaseService implements GradeCreditRuleService {

    private final GradeCreditRuleListQryExe gradeCreditRuleListQryExe;
    private final GradeCreditRuleAddCmdExe gradeCreditRuleAddCmdExe;

    public GradeCreditRuleServiceImpl(GradeCreditRuleListQryExe gradeCreditRuleListQryExe,
                                      GradeCreditRuleAddCmdExe gradeCreditRuleAddCmdExe) {
        this.gradeCreditRuleListQryExe = gradeCreditRuleListQryExe;
        this.gradeCreditRuleAddCmdExe = gradeCreditRuleAddCmdExe;
    }

    @Override
    public PageResponse<GradeCreditRuleListCO> list(GradeCreditRuleListQry cmd) {
        try {
            return gradeCreditRuleListQryExe.execute(cmd);
        } catch (Exception e) {
            Response response = getErrorResponse(e);
            return PageResponse.buildFailure(
                    response.getErrCode(),
                    response.getErrMessage()
            );
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response add(GradeCreditRuleAddCmd cmd) {
        try {
            return gradeCreditRuleAddCmdExe.execute(cmd);
        } catch (Exception e) {
            TransactionSupport.setRollback();
            log.error("添加用户等级规则失败 : ", e);
            return getErrorResponse(e);
        }
    }
}
