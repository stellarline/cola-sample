package net.futureorigin.architecture.sample.cola.command.user;

import com.alibaba.cola.dto.Response;
import net.futureorigin.architecture.sample.cola.dto.command.user.UserPasswordResetCmd;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.user.UserDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper.UserMapper;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * UserPasswordResetCmdExe
 * <p></p>
 *
 * @author Leander Lee on 2021/9/4.
 */
@Component
public class UserPasswordResetCmdExe extends BaseUserCmdExe {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserPasswordResetCmdExe(UserMapper userMapper,
                                   UserRepository userRepository,
                                   PasswordEncoder passwordEncoder) {
        super(userMapper);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Response execute(UserPasswordResetCmd cmd) {
        UserDO userDO = checkAndGet(cmd.getId());
        userDO.setPassword(passwordEncoder.encode(cmd.getPassword()));
        userRepository.save(userDO);
        return Response.buildSuccess();
    }
}
