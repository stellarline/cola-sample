package net.futureorigin.architecture.sample.cola.command.gradecreditrule;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.cola.dto.PageResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.futureorigin.architecture.sample.cola.dto.clientobject.gradecreditrule.GradeCreditRuleListCO;
import net.futureorigin.architecture.sample.cola.dto.command.gradecreditrule.GradeCreditRuleListQry;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.gradecreditrule.GradeCreditRuleDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper.GradeCreditRuleMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * GradeCreditRuleListQryCmdExe
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@Component
public class GradeCreditRuleListQryExe {

    private final GradeCreditRuleMapper gradeCreditRuleMapper;

    public GradeCreditRuleListQryExe(GradeCreditRuleMapper gradeCreditRuleMapper) {
        this.gradeCreditRuleMapper = gradeCreditRuleMapper;
    }

    public PageResponse<GradeCreditRuleListCO> execute(GradeCreditRuleListQry cmd) {
        PageHelper.startPage(cmd.getPageIndex(), cmd.getPageSize(), true);
        Page<GradeCreditRuleDO> gradeCreditRuleDOPage = gradeCreditRuleMapper.findAll();
        if (CollectionUtil.isEmpty(gradeCreditRuleDOPage)) {
            return PageResponse.buildSuccess();
        }
        List<GradeCreditRuleDO> gradeCreditRuleDOS = gradeCreditRuleDOPage.getResult();
        List<GradeCreditRuleListCO> gradeCreditRuleListCOS = new ArrayList<>();
        gradeCreditRuleDOS.forEach(gradeCreditRuleDO -> {
            GradeCreditRuleListCO ruleListCO = new GradeCreditRuleListCO();
            BeanUtil.copyProperties(gradeCreditRuleDO, ruleListCO);
            gradeCreditRuleListCOS.add(ruleListCO);
        });
        return PageResponse.of(gradeCreditRuleListCOS,
                Math.toIntExact(gradeCreditRuleDOPage.getTotal()), gradeCreditRuleDOPage.getPageSize(), gradeCreditRuleDOPage.getPageNum());
    }
}
