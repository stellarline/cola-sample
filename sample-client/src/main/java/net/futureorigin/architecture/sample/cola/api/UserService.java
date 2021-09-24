package net.futureorigin.architecture.sample.cola.api;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import net.futureorigin.architecture.sample.cola.dto.clientobject.user.UserCO;
import net.futureorigin.architecture.sample.cola.dto.clientobject.user.UserListCO;
import net.futureorigin.architecture.sample.cola.dto.command.user.*;

/**
 * UserService
 * <p></p>
 *
 * @author Leander Lee on 2021/9/1.
 */
public interface UserService {

    Response add(UserAddCmd cmd);

    Response update(UserUpdateCmd cmd);

    PageResponse<UserListCO> list(UserListQry cmd);

    SingleResponse<UserCO> get(UserGetQry cmd);

    Response resetPassword(UserPasswordResetCmd cmd);

    Response updateState(UserStateUpdateCmd cmd);

    Response refreshGrade(UserGradeRefreshCmd cmd);
}
