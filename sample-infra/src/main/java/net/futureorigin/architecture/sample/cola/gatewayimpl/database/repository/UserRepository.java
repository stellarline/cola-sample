package net.futureorigin.architecture.sample.cola.gatewayimpl.database.repository;

import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.user.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 * <p></p>
 *
 * @author Leander Lee create on 2021/8/30.
 */
@Repository
public interface UserRepository extends JpaRepository<UserDO, Long> {

}

