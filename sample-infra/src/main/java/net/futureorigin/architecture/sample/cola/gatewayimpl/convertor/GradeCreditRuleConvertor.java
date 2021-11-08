package net.futureorigin.architecture.sample.cola.gatewayimpl.convertor;

import cn.hutool.core.bean.BeanUtil;
import net.futureorigin.architecture.sample.cola.domain.model.gradecreditrule.GradeCreditRule;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.gradecreditrule.GradeCreditRuleDO;

/**
 * GradeCreditRuleConvertor
 * <p></p>
 *
 * @author Leander Lee on 2021/9/2.
 */
public class GradeCreditRuleConvertor {

    public static GradeCreditRule toEntity(GradeCreditRuleDO gradeCreditRuleDO) {
        GradeCreditRule gradeCreditRule = new GradeCreditRule();
        BeanUtil.copyProperties(gradeCreditRuleDO, gradeCreditRule);
        return gradeCreditRule;
    }
}
