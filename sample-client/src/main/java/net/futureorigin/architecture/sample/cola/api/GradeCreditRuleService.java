package net.futureorigin.architecture.sample.cola.api;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import net.futureorigin.architecture.sample.cola.dto.clientobject.gradecreditrule.GradeCreditRuleListCO;
import net.futureorigin.architecture.sample.cola.dto.command.gradecreditrule.GradeCreditRuleAddCmd;
import net.futureorigin.architecture.sample.cola.dto.command.gradecreditrule.GradeCreditRuleListQry;

/**
 * GradeCreditRuleService
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
public interface GradeCreditRuleService {

    PageResponse<GradeCreditRuleListCO> list(GradeCreditRuleListQry cmd);

    Response add(GradeCreditRuleAddCmd creditRuleAddCmd);
}
