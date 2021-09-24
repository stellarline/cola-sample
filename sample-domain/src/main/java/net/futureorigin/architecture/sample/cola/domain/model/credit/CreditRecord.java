package net.futureorigin.architecture.sample.cola.domain.model.credit;

import com.alibaba.cola.domain.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.futureorigin.architecture.sample.cola.domain.model.BaseDomain;

/**
 * CreditRecord
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@Getter
@Setter
@ToString
@Entity
public class CreditRecord extends BaseDomain {
    /**
     * 充值
     */
    public final static int TYPE_ADD = 1;
    /**
     * 消费
     */
    public final static int TYPE_CONSUME = 2;

    private Long userId;

    /**
     * 积分数额
     */
    private Double credit;

    /**
     * 积分类型
     * 1：消费
     * 2：充值
     */
    private Integer type;
}
