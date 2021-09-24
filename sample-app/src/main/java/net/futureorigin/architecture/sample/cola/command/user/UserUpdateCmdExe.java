package net.futureorigin.architecture.sample.cola.command.user;

import cn.hutool.core.util.StrUtil;
import net.futureorigin.architecture.sample.cola.domain.gateway.UserGateway;
import net.futureorigin.architecture.sample.cola.domain.model.user.User;
import net.futureorigin.architecture.sample.cola.dto.command.user.UserUpdateCmd;
import net.futureorigin.architecture.sample.cola.gatewayimpl.convertor.UserConvertor;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.user.UserDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper.UserMapper;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.repository.UserRepository;
import org.springframework.stereotype.Component;

/**
 * UserUpdateCmdExe
 * <p></p>
 *
 * @author Leander Lee on 2021/9/3.
 */
@Component
public class UserUpdateCmdExe extends BaseUserCmdExe {

    private final UserGateway userGateway;

    public UserUpdateCmdExe(UserGateway userGateway,
                            UserMapper userMapper) {
        super(userMapper);
        this.userGateway = userGateway;
    }

    public boolean execute(UserUpdateCmd cmd) {
        validateMobileFormat(cmd.getMobile());
        UserDO userDO = checkAndGet(cmd.getId());
        User user = UserConvertor.toEntity(userDO);
        user.setName(cmd.getName());
        if (StrUtil.isNotBlank(cmd.getMobile()) && !cmd.getMobile().equals(user.getMobile())) {
            // check unique mobile
            checkIsMobileEquals(cmd.getMobile(), user.getId());
            user.setMobile(cmd.getMobile());
        }
        return userGateway.update(user);
    }
}
