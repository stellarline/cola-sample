package net.futureorigin.architecture.sample.cola.domain.model.role;

import com.alibaba.cola.domain.Entity;
import net.futureorigin.architecture.sample.cola.domain.model.BaseDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * UserRole
 * <p></p>
 *
 * @author Leander Lee on 2021/9/10.
 */
@Getter
@Setter
@ToString
@Entity
public class UserRole extends BaseDomain {
    public static final String ROLE_ADMIN = "role_admin";
    public static final String ROLE_USER = "role_user";

    private Long userId;

    private String roleCode;
}
