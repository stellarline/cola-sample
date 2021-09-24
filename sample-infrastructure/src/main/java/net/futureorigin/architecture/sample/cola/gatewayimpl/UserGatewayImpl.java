package net.futureorigin.architecture.sample.cola.gatewayimpl;

import net.futureorigin.architecture.sample.cola.domain.gateway.UserGateway;
import net.futureorigin.architecture.sample.cola.domain.model.user.User;
import net.futureorigin.architecture.sample.cola.gatewayimpl.convertor.UserConvertor;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.user.UserDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * UserGatewayImpl
 * <p></p>
 *
 * @author Leander Lee create on 2021/8/30.
 */
@Component
public class UserGatewayImpl implements UserGateway {

    @Resource
    private UserRepository userRepository;

    @Override
    public User add(User user) {
        UserDO userDO = UserConvertor.toDO(user);
        return UserConvertor.toEntity(userRepository.save(userDO));
    }

    @Override
    public boolean update(User user) {
        userRepository.save(UserConvertor.toDO(user));
        return true;
    }
}
