package net.futureorigin.architecture.sample.cola.domain.gateway;

import net.futureorigin.architecture.sample.cola.domain.model.user.User;

/**
 * UserGateway
 * <p></p>
 *
 * @author Leander Lee create on 2021/8/30.
 */
public interface UserGateway {

    User add(User user);

    boolean update(User user);
}
