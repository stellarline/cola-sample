package net.futureorigin.architecture.sample.cola.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.Response;
import lombok.extern.slf4j.Slf4j;
import net.futureorigin.architecture.sample.cola.api.UserRoleService;
import net.futureorigin.architecture.sample.cola.command.role.UserRoleAddCmdExe;
import net.futureorigin.architecture.sample.cola.dto.command.role.UserRoleAddCmd;
import net.futureorigin.architecture.sample.cola.support.TransactionSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserRoleServiceImpl
 * <p></p>
 *
 * @author Leander Lee on 2021/9/12.
 */
@Service
@CatchAndLog
@Slf4j
public class UserRoleServiceImpl extends BaseService implements UserRoleService {

    private final UserRoleAddCmdExe userRoleAddCmdExe;

    public UserRoleServiceImpl(UserRoleAddCmdExe userRoleAddCmdExe) {
        this.userRoleAddCmdExe = userRoleAddCmdExe;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response add(UserRoleAddCmd cmd) {
        try {
            return userRoleAddCmdExe.execute(cmd);
        } catch (Exception e) {
            TransactionSupport.setRollback();
            log.error("添加用户角色失败 : ", e);
            return getErrorResponse(e);
        }
    }
}
