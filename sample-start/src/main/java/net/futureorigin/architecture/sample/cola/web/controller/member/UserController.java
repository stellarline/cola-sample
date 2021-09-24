package net.futureorigin.architecture.sample.cola.web.controller.member;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.futureorigin.architecture.sample.cola.api.UserService;
import net.futureorigin.architecture.sample.cola.context.UserContext;
import net.futureorigin.architecture.sample.cola.dto.clientobject.user.UserCO;
import net.futureorigin.architecture.sample.cola.dto.command.user.UserGetQry;
import net.futureorigin.architecture.sample.cola.dto.command.user.UserPasswordResetCmd;
import net.futureorigin.architecture.sample.cola.web.security.SecurityUtils;
import org.springframework.web.bind.annotation.*;

/**
 * UserController
 * <p></p>
 *
 * @author Leander Lee on 2021/9/4.
 */
@RestController
@RequestMapping("member/user")
@Api(tags = "用户模块")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("info")
    @ApiOperation("用户信息")
    public SingleResponse<UserCO> userInfo() {
        UserGetQry userGetQry = new UserGetQry();
        UserContext userContext = SecurityUtils.getUserContext();
        userGetQry.setUserContext(userContext);
        userGetQry.setId(userContext.getLoggedInUserId());

        return userService.get(userGetQry);
    }

    @PostMapping("resetPassword")
    @ApiOperation("重置密码")
    public Response resetPassword(@RequestParam("password") String password) {
        UserPasswordResetCmd cmd = new UserPasswordResetCmd();
        UserContext userContext = SecurityUtils.getUserContext();
        cmd.setUserContext(userContext);
        cmd.setPassword(password);
        cmd.setId(userContext.getLoggedInUserId());

        return userService.resetPassword(cmd);
    }
}
