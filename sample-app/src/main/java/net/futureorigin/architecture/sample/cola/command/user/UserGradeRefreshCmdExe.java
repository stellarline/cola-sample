package net.futureorigin.architecture.sample.cola.command.user;

import com.alibaba.cola.dto.Response;
import net.futureorigin.architecture.sample.cola.dto.command.user.UserGradeRefreshCmd;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.credit.CreditDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.gradecreditrule.GradeCreditRuleDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.user.UserDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper.CreditMapper;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper.GradeCreditRuleMapper;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * UserGradeRefreshCmdExe
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@Component
public class UserGradeRefreshCmdExe {

    private final UserMapper userMapper;
    private final GradeCreditRuleMapper ruleMapper;
    private final CreditMapper creditMapper;

    public UserGradeRefreshCmdExe(UserMapper userMapper,
                                  GradeCreditRuleMapper ruleMapper,
                                  CreditMapper creditMapper) {
        this.userMapper = userMapper;
        this.ruleMapper = ruleMapper;
        this.creditMapper = creditMapper;
    }

    public Response execute(UserGradeRefreshCmd cmd) {
        UserDO userDO = userMapper.findById(cmd.getUserId());
        if (null != userDO) {
            CreditDO creditDO = creditMapper.findByUserId(cmd.getUserId());
            GradeCreditRuleDO gradeCreditRuleDO = ruleMapper.findUpgradedGrade(creditDO.getBalance());
            if (!userDO.getGradeCode().equals(gradeCreditRuleDO.getCode())) {
                userDO.setGradeCode(gradeCreditRuleDO.getCode());
                userDO.setModifier(cmd.getUserContext().getLoggedInUserId());
                userDO.setModifiedTime(new Date());
                userMapper.updateSelectiveById(userDO);
            }
        }
        return Response.buildSuccess();
    }
}
