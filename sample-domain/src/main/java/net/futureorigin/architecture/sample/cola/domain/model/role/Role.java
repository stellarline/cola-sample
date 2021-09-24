package net.futureorigin.architecture.sample.cola.domain.model.role;

import com.alibaba.cola.domain.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.futureorigin.architecture.sample.cola.domain.model.BaseDomain;

/**
 * Role
 * <p></p>
 *
 * @author Leander Lee on 2021/9/11.
 */
@Entity
@Getter
@Setter
@ToString
public class Role extends BaseDomain {

    private String code;

    private String name;
}
