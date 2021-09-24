package net.futureorigin.architecture.sample.cola.gatewayimpl.database.repository;

import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.gradecreditrule.GradeCreditRuleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * GradeCreditRuleRepository
 * <p></p>
 *
 * @author Leander Lee on 2021/9/2.
 */
@Repository
public interface GradeCreditRuleRepository extends JpaRepository<GradeCreditRuleDO, Long> {

    @Query(value = "select * from sample_grade_credit_rule order by grade_order asc limit 1",
            nativeQuery = true)
    List<GradeCreditRuleDO> findLowestGrade();
}
