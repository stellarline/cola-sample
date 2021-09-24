package net.futureorigin.architecture.sample.cola.gatewayimpl;

import cn.hutool.core.collection.CollectionUtil;
import net.futureorigin.architecture.sample.cola.common.exception.ObjectNotFoundException;
import net.futureorigin.architecture.sample.cola.domain.gateway.GradeCreditRuleGateway;
import net.futureorigin.architecture.sample.cola.domain.model.gradecreditrule.GradeCreditRule;
import net.futureorigin.architecture.sample.cola.gatewayimpl.convertor.GradeCreditRuleConvertor;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.gradecreditrule.GradeCreditRuleDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.repository.GradeCreditRuleRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * GradeCreditRuleGatewayImpl
 * <p></p>
 *
 * @author Leander Lee on 2021/9/2.
 */
@Component
public class GradeCreditRuleGatewayImpl implements GradeCreditRuleGateway {

    @Resource
    private GradeCreditRuleRepository repository;

    @Override
    public boolean add(GradeCreditRule gradeCreditRule) {
        return false;
    }

    @Override
    public boolean update(GradeCreditRule gradeCreditRule) {
        return false;
    }


    @Override
    public GradeCreditRule getLowestGrade() {
        List<GradeCreditRuleDO> gradeCreditRuleDOS = repository.findLowestGrade();
        if (CollectionUtil.isEmpty(gradeCreditRuleDOS)) {
            throw new ObjectNotFoundException("Cannot find the lowest grade.Create grade credit rule before create user.");
        }
        return GradeCreditRuleConvertor.toEntity(gradeCreditRuleDOS.get(0));
    }
}
