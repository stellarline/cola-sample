package net.futureorigin.architecture.sample.cola.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import net.futureorigin.architecture.sample.cola.api.CreditService;
import net.futureorigin.architecture.sample.cola.api.UserRoleService;
import net.futureorigin.architecture.sample.cola.api.UserService;
import net.futureorigin.architecture.sample.cola.command.user.*;
import net.futureorigin.architecture.sample.cola.common.exception.BizExceptionCode;
import net.futureorigin.architecture.sample.cola.domain.model.credit.Credit;
import net.futureorigin.architecture.sample.cola.domain.model.role.UserRole;
import net.futureorigin.architecture.sample.cola.domain.model.user.User;
import net.futureorigin.architecture.sample.cola.dto.clientobject.user.UserCO;
import net.futureorigin.architecture.sample.cola.dto.clientobject.user.UserListCO;
import net.futureorigin.architecture.sample.cola.dto.command.credit.CreditAddCmd;
import net.futureorigin.architecture.sample.cola.dto.command.role.UserRoleAddCmd;
import net.futureorigin.architecture.sample.cola.dto.command.user.*;
import net.futureorigin.architecture.sample.cola.support.TransactionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserServiceImpl
 * <p></p>
 *
 * @author Leander Lee on 2021/9/1.
 */
@Service
@Slf4j
@CatchAndLog
public class UserServiceImpl extends BaseService implements UserService {

    private final UserAddCmdExe addCmdExe;
    private final UserUpdateCmdExe updateCmdExe;
    private final UserListQryExe listQryCmdExe;
    private final UserGetQryExe getQryCmdExe;
    private final UserPasswordResetCmdExe passwordResetCmdExe;
    private final UserStateUpdateCmdExe stateUpdateCmdExe;
    private final UserGradeRefreshCmdExe gradeRefreshCmdExe;

    @Autowired
    @Lazy
    private CreditService creditService;

    @Autowired
    private UserRoleService userRoleService;

    public UserServiceImpl(UserAddCmdExe addCmdExe,
                           UserUpdateCmdExe updateCmdExe,
                           UserListQryExe listQryCmdExe,
                           UserGetQryExe getQryCmdExe,
                           UserPasswordResetCmdExe passwordResetCmdExe,
                           UserStateUpdateCmdExe stateUpdateCmdExe,
                           UserGradeRefreshCmdExe gradeRefreshCmdExe) {
        this.addCmdExe = addCmdExe;
        this.updateCmdExe = updateCmdExe;
        this.listQryCmdExe = listQryCmdExe;
        this.getQryCmdExe = getQryCmdExe;
        this.passwordResetCmdExe = passwordResetCmdExe;
        this.stateUpdateCmdExe = stateUpdateCmdExe;
        this.gradeRefreshCmdExe = gradeRefreshCmdExe;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response add(UserAddCmd cmd) {
        try {
            User savedUser = addCmdExe.execute(cmd);

            // 设置用户角色
            UserRoleAddCmd userRoleAddCmd = new UserRoleAddCmd(savedUser.getId(), UserRole.ROLE_USER);
            userRoleAddCmd.setUserContext(cmd.getUserContext());
            Response addRoleResp = userRoleService.add(userRoleAddCmd);
            if (!addRoleResp.isSuccess()) {
                throw new BizException(addRoleResp.getErrCode(), addRoleResp.getErrMessage());
            }

            // 新用户默认赠送积分
            CreditAddCmd creditAddCmd = new CreditAddCmd(savedUser.getId(), Credit.GIVING_BALANCE);
            creditAddCmd.setUserContext(cmd.getUserContext());
            Response creditAddResp = creditService.add(creditAddCmd);
            if (!creditAddResp.isSuccess()) {
                throw new BizException(creditAddResp.getErrCode(), creditAddResp.getErrMessage());
            }

            return Response.buildSuccess();
        } catch (Exception e) {
            TransactionSupport.setRollback();
            log.error("用户添加失败 : ", e);
            return getErrorResponse(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response update(UserUpdateCmd cmd) {
        try {
            return updateCmdExe.execute(cmd) ?
                    Response.buildSuccess() :
                    Response.buildFailure(
                            BizExceptionCode.USER_UPDATE_FAILURE.getCode(),
                            BizExceptionCode.USER_UPDATE_FAILURE.getMessage()
                    );
        } catch (Exception e) {
            TransactionSupport.setRollback();
            log.error("用户更新失败 : ", e);
            return getErrorResponse(e);
        }
    }

    @Override
    public PageResponse<UserListCO> list(UserListQry cmd) {
        try {
            return listQryCmdExe.execute(cmd);
        } catch (Exception e) {
            log.error("查询用户列表失败 : ", e);
            Response response = getErrorResponse(e);
            return PageResponse.buildFailure(response.getErrCode(), response.getErrMessage());
        }
    }

    @Override
    public SingleResponse<UserCO> get(UserGetQry cmd) {
        try {
            return getQryCmdExe.execute(cmd);
        } catch (Exception e) {
            log.error("查询用户信息失败 : ", e);
            Response response = getErrorResponse(e);
            return SingleResponse.buildFailure(response.getErrCode(), response.getErrMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response resetPassword(UserPasswordResetCmd cmd) {
        try {
            return passwordResetCmdExe.execute(cmd);
        } catch (Exception e) {
            TransactionSupport.setRollback();
            log.error("用户密码重置失败 : ", e);
            return getErrorResponse(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response updateState(UserStateUpdateCmd cmd) {
        try {
            return stateUpdateCmdExe.execute(cmd);
        } catch (Exception e) {
            TransactionSupport.setRollback();
            log.error("更新用户状态失败 : ", e);
            return getErrorResponse(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response refreshGrade(UserGradeRefreshCmd cmd) {
        try {
            return gradeRefreshCmdExe.execute(cmd);
        } catch (Exception e) {
            TransactionSupport.setRollback();
            log.error("用户等级刷新失败 : ", e);
            return getErrorResponse(e);
        }
    }

}
