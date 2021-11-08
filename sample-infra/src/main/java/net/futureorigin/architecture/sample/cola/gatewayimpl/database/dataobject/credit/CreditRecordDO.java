package net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.credit;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.BaseDO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * CreditRecordDO
 * <p></p>
 *
 * @author Leander Lee on 2021/8/31.
 */
@Getter
@Setter
@ToString
@Table(name = "sample_credit_record")
@Entity
public class CreditRecordDO extends BaseDO {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 积分数额
     */
    @Column(nullable = false)
    private Double credit;

    /**
     * 积分类型
     * 1：消费
     * 2：充值
     */
    @Column(nullable = false)
    private Integer type;

    @Transient
    private String userMobile;

    @Transient
    private String creatorName;

}
