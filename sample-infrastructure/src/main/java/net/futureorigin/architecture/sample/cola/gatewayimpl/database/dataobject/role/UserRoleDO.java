package net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.role;

import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UserRole
 * <p></p>
 *
 * @author Leander Lee on 2021/9/10.
 */
@Getter
@Setter
@ToString
@Table(name = "sample_user_role")
@Entity
public class UserRoleDO extends BaseDO {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_codes")
    private String roleCodes;
}
