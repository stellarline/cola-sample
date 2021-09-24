package net.futureorigin.architecture.sample.cola.command.user;

import com.alibaba.cola.dto.Response;
import net.futureorigin.architecture.sample.cola.dto.command.user.UserStateUpdateCmd;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.user.UserDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper.UserMapper;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.repository.UserRepository;
import org.springframework.stereotype.Component;

/**
 * UserStateUpdateCmdExe
 * <p></p>
 *
 * @author Leander Lee on 2021/9/4.
 */
@Component
public class UserStateUpdateCmdExe extends BaseUserCmdExe {

    private final UserRepository userRepository;

    public UserStateUpdateCmdExe(UserMapper userMapper,
                                 UserRepository userRepository) {
        super(userMapper);
        this.userRepository = userRepository;
    }

    public Response execute(UserStateUpdateCmd cmd) {
        UserDO userDO = checkAndGet(cmd.getId());
        userDO.setState(cmd.getState());
        userRepository.save(userDO);
        return Response.buildSuccess();
    }
}
