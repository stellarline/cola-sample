package net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.role;

import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * RoleDO
 * <p></p>
 *
 * @author Leander Lee on 2021/9/10.
 */
@Getter
@Setter
@ToString
@Table(name = "sample_role")
@Entity
public class RoleDO extends BaseDO {

    @Column(length = 32)
    private String code;

    private String name;
}
