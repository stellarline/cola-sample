package net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper;

import com.github.pagehelper.Page;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.credit.CreditRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * CreditRecordMapper
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@Mapper
public interface CreditRecordMapper {

    Page<CreditRecordDO> findAll();

    Page<CreditRecordDO> findByUserId(@Param("userId") Long userId);

    Page<CreditRecordDO> findByUserMobile(@Param("userMobile") String userMobile);
}
