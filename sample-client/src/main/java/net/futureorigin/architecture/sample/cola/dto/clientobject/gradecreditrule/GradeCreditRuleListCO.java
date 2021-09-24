package net.futureorigin.architecture.sample.cola.dto.clientobject.gradecreditrule;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.futureorigin.architecture.sample.cola.dto.clientobject.ClientObject;
import lombok.*;

/**
 * GradeCreditRuleListCO
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GradeCreditRuleListCO extends ClientObject {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

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
