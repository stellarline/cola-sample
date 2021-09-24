package net.futureorigin.architecture.sample.cola.domain.model.gradecreditrule;

import com.alibaba.cola.domain.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.futureorigin.architecture.sample.cola.domain.model.BaseDomain;

/**
 * GradeCreditRule
 * <p></p>
 *
 * @author Leander Lee on 2021/9/2.
 */
@Getter
@Setter
@ToString
@Entity
public class GradeCreditRule extends BaseDomain {

    /**
     * 等级编码
     */
    private String code;

    /**
     * 等级显示名称
     */
    private String plainText;

    /**
     * 达到该等级所需积分
     */
    private Double upgradeLimitCredit;

    /**
     * 该等级对应的单次积分扣除额度
     */
    private Double creditDeductQuota;

    /**
     * 表示等级顺序
     */
    private Integer gradeOrder;
}
