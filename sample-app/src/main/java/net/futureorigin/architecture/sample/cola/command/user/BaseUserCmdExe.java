package net.futureorigin.architecture.sample.cola.command.user;

import cn.hutool.core.util.PhoneUtil;
import net.futureorigin.architecture.sample.cola.common.exception.BizExceptionCode;
import net.futureorigin.architecture.sample.cola.common.exception.ObjectCheckException;
import net.futureorigin.architecture.sample.cola.common.exception.ObjectNotFoundException;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.user.UserDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper.UserMapper;

import java.util.Objects;

/**
 * BaseUserCmdExe
 * <p></p>
 *
 * @author Leander Lee on 2021/9/4.
 */
public abstract class BaseUserCmdExe {

    private final UserMapper userMapper;

    public BaseUserCmdExe(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    protected UserDO checkAndGet(Long id) {
        UserDO userDO = userMapper.findById(id);
        if (null == userDO) {
            throw new ObjectNotFoundException(
                    BizExceptionCode.USER_NOT_FOUND.getCode(),
                    BizExceptionCode.USER_NOT_FOUND.getMessage()
            );
        }
        return userDO;
    }

    protected void checkIsMobileExist(String mobile) {
        UserDO userDO = userMapper.findByMobile(mobile);
        if (null != userDO) {
            throw new ObjectCheckException("已存在该手机号的用户。");
        }
    }

    protected void checkIsMobileEquals(String mobile, Long userId) {
        UserDO userDO = userMapper.findByMobile(mobile);
        if (null != userDO && !Objects.equals(userDO.getId(), userId)) {
            throw new ObjectCheckException("已存在该手机号的用户。");
        }
    }

    protected void validateMobileFormat(String mobile) {
        if (!PhoneUtil.isMobile(mobile)) {
            throw new ObjectCheckException("手机号码格式不正确。");
        }
    }
}
