package net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.gradecreditrule;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.BaseDO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * GradeCreditRuleDO
 * <p></p>
 *
 * @author Leander Lee on 2021/8/31.
 */
@Getter
@Setter
@ToString
@Table(name = "sample_grade_credit_rule")
@Entity
public class GradeCreditRuleDO extends BaseDO {

    /**
     * 等级编码
     */
    @Column(nullable = false, length = 16)
    private String code;

    /**
     * 等级显示名称
     */
    @Column(name = "plain_text", nullable = false, length = 10)
    private String plainText;

    /**
     * 达到该等级所需积分
     */
    @Column(name = "upgrade_limit_credit")
    private Double upgradeLimitCredit;

    /**
     * 该等级对应的单次积分扣除额度
     */
    @Column(name = "credit_deduct_quota")
    private Double creditDeductQuota;

    /**
     * 表示等级顺序
     */
    @Column(name = "grade_order")
    private Integer gradeOrder;


}
