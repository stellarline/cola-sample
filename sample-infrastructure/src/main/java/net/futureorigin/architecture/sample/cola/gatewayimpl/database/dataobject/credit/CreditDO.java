package net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.credit;

import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CreditDO
 * <p></p>
 *
 * @author Leander Lee on 2021/8/31.
 */
@Getter
@Setter
@ToString
@Table(name = "sample_credit")
@Entity
public class CreditDO extends BaseDO {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 剩余积分
     */
    @Column(nullable = false)
    private Double balance;

}
