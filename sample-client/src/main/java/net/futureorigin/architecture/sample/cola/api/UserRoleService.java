package net.futureorigin.architecture.sample.cola.api;

import com.alibaba.cola.dto.Response;
import net.futureorigin.architecture.sample.cola.dto.command.role.UserRoleAddCmd;

/**
 * UserRoleService
 * <p></p>
 *
 * @author Leander Lee on 2021/9/12.
 */
public interface UserRoleService {

    Response add(UserRoleAddCmd cmd);
}
