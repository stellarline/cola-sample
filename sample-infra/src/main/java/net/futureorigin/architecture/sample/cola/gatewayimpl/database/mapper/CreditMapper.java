package net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper;

import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.credit.CreditDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * CreditMapper
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@Mapper
public interface CreditMapper {

    CreditDO findByUserId(@Param("userId") Long userId);
}
