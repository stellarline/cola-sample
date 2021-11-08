package net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper;

import com.github.pagehelper.Page;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.user.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * UserMapper
 * <p></p>
 *
 * @author Leander Lee on 2021/9/4.
 */
@Mapper
public interface UserMapper {

    UserDO findById(@Param("id") Long id);

    UserDO findByMobile(@Param("mobile") String mobile);

    Page<UserDO> findBySelective(@Param("user") UserDO user);

    Integer updateSelectiveById(@Param("user") UserDO user);
}
