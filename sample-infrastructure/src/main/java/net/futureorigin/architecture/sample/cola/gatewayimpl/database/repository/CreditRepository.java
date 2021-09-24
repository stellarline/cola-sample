package net.futureorigin.architecture.sample.cola.gatewayimpl.database.repository;

import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.credit.CreditDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CreditRepository
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@Repository
public interface CreditRepository extends JpaRepository<CreditDO, Long> {
}
