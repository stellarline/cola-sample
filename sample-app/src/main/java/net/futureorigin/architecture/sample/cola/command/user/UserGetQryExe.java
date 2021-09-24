package net.futureorigin.architecture.sample.cola.command.user;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.cola.dto.SingleResponse;
import net.futureorigin.architecture.sample.cola.common.exception.BizExceptionCode;
import net.futureorigin.architecture.sample.cola.dto.clientobject.user.UserCO;
import net.futureorigin.architecture.sample.cola.dto.command.user.UserGetQry;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.user.UserDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper.UserMapper;
import org.springframework.stereotype.Component;

/**
 * UserGetQryCmdExe
 * <p></p>
 *
 * @author Leander Lee on 2021/9/4.
 */
@Component
public class UserGetQryExe {

    private final UserMapper userMapper;

    public UserGetQryExe(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public SingleResponse<UserCO> execute(UserGetQry cmd) {
        UserDO userDO = null;
        if (null != cmd.getId()) {
            userDO = userMapper.findById(cmd.getId());
        } else if (null != cmd.getMobile()) {
            userDO = userMapper.findByMobile(cmd.getMobile());
        }
        if (null == userDO) {
            return SingleResponse.buildFailure(
                    BizExceptionCode.USER_NOT_FOUND.getCode(),
                    BizExceptionCode.USER_NOT_FOUND.getMessage()
            );
        }
        UserCO userCO = new UserCO();
        BeanUtil.copyProperties(userDO, userCO);
        return SingleResponse.of(userCO);
    }
}
