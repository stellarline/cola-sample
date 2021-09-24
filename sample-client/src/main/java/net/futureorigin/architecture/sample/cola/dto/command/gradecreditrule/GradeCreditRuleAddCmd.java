package net.futureorigin.architecture.sample.cola.dto.command.gradecreditrule;

import net.futureorigin.architecture.sample.cola.dto.command.CommonCommand;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * GradeCreditRuleAddCmd
 * <p></p>
 *
 * @author Leander Lee create on 2021/9/6.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GradeCreditRuleAddCmd extends CommonCommand {

    /**
     * 等级编码
     */
    @NotBlank
    private String code;

    /**
     * 等级显示名称
     */
    @NotBlank
    private String plainText;

    /**
     * 达到该等级所需积分
     */
    @NotNull
    private Double upgradeLimitCredit;

    /**
     * 该等级对应的单次积分扣除额度
     */
    @NotNull
    private Double creditDeductQuota;

    /**
     * 表示等级顺序
     */
    @NotNull
    private Integer gradeOrder;
}
