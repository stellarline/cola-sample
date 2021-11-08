package net.futureorigin.architecture.sample.cola.gatewayimpl.database.repository;

import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.role.UserRoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRoleRepository
 * <p></p>
 *
 * @author Leander Lee on 2021/9/12.
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleDO, Long> {
}
