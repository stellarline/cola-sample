package net.futureorigin.architecture.sample.cola.command.gradecreditrule;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import net.futureorigin.architecture.sample.cola.common.exception.BizExceptionCode;
import net.futureorigin.architecture.sample.cola.context.UserContext;
import net.futureorigin.architecture.sample.cola.dto.command.gradecreditrule.GradeCreditRuleAddCmd;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.gradecreditrule.GradeCreditRuleDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper.GradeCreditRuleMapper;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.repository.GradeCreditRuleRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * GradeCreditRuleAddCmdExe
 * <p></p>
 *
 * @author Leander Lee create on 2021/9/6.
 */
@Component
public class GradeCreditRuleAddCmdExe {

    private final GradeCreditRuleMapper gradeCreditRuleMapper;
    private final GradeCreditRuleRepository gradeCreditRuleRepository;

    public GradeCreditRuleAddCmdExe(GradeCreditRuleMapper gradeCreditRuleMapper,
                                    GradeCreditRuleRepository gradeCreditRuleRepository) {
        this.gradeCreditRuleMapper = gradeCreditRuleMapper;
        this.gradeCreditRuleRepository = gradeCreditRuleRepository;
    }

    public Response execute(GradeCreditRuleAddCmd cmd) {
        GradeCreditRuleDO gradeCreditRuleDO = gradeCreditRuleMapper.findByGradeOrder(cmd.getGradeOrder());
        if (null != gradeCreditRuleDO) {
            throw new BizException(
                    BizExceptionCode.OBJ_CHECK_FAILURE.getCode(),
                    "已存在相同的等级级别。"
            );
        }

        GradeCreditRuleDO newRuleDO = new GradeCreditRuleDO();
        BeanUtil.copyProperties(cmd, newRuleDO);
        UserContext userContext = cmd.getUserContext();
        newRuleDO.setCreator(userContext.getLoggedInUserId());
        newRuleDO.setModifier(userContext.getLoggedInUserId());
        newRuleDO.setCreationTime(new Date());
        newRuleDO.setModifiedTime(new Date());
        gradeCreditRuleRepository.save(newRuleDO);
        return Response.buildSuccess();
    }
}
