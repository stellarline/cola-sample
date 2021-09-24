package net.futureorigin.architecture.sample.cola.command.role;

import com.alibaba.cola.dto.Response;
import net.futureorigin.architecture.sample.cola.context.UserContext;
import net.futureorigin.architecture.sample.cola.dto.command.role.UserRoleAddCmd;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.role.UserRoleDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.repository.UserRoleRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * UserRoleAddCmdExe
 * <p></p>
 *
 * @author Leander Lee on 2021/9/11.
 */
@Component
public class UserRoleAddCmdExe {

    private final UserRoleRepository userRoleRepository;

    public UserRoleAddCmdExe(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public Response execute(UserRoleAddCmd cmd) {
        UserRoleDO userRoleDO = new UserRoleDO();
        userRoleDO.setUserId(cmd.getUserId());
        userRoleDO.setRoleCodes(cmd.getRoleCode());
        UserContext userContext = cmd.getUserContext();
        userRoleDO.setCreator(userContext.getLoggedInUserId());
        userRoleDO.setModifier(userContext.getLoggedInUserId());
        userRoleDO.setCreationTime(new Date());
        userRoleDO.setModifiedTime(new Date());
        userRoleRepository.save(userRoleDO);

        return Response.buildSuccess();

    }


}
