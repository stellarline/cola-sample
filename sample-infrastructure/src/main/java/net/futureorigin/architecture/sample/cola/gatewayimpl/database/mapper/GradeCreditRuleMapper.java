package net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper;

import com.github.pagehelper.Page;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.gradecreditrule.GradeCreditRuleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * GradeCreditRuleMapper
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@Mapper
public interface GradeCreditRuleMapper {

    Page<GradeCreditRuleDO> findAll();

    GradeCreditRuleDO findUpgradedGrade(Double currentCredit);

    GradeCreditRuleDO findByGradeOrder(Integer gradeOrder);

    GradeCreditRuleDO findByGradeCode(@Param("gradeCode") String gradeCode);
}
