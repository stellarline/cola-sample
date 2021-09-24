package net.futureorigin.architecture.sample.cola.domain.gateway;

import net.futureorigin.architecture.sample.cola.domain.model.gradecreditrule.GradeCreditRule;

/**
 * GradeCreditRuleGateway
 * <p></p>
 *
 * @author Leander Lee on 2021/9/2.
 */
public interface GradeCreditRuleGateway {

    boolean add(GradeCreditRule gradeCreditRule);

    boolean update(GradeCreditRule gradeCreditRule);

    GradeCreditRule getLowestGrade();
}
