package net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper;

import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.role.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserRoleMapper
 * <p></p>
 *
 * @author Leander Lee on 2021/9/10.
 */
@Mapper
public interface UserRoleMapper {

    List<UserRoleDO> find(@Param("userId") Long userId);
}
