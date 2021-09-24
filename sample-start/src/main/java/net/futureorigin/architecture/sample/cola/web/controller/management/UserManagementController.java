package net.futureorigin.architecture.sample.cola.web.controller.management;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import net.futureorigin.architecture.sample.cola.api.UserService;
import net.futureorigin.architecture.sample.cola.dto.clientobject.user.UserCO;
import net.futureorigin.architecture.sample.cola.dto.clientobject.user.UserListCO;
import net.futureorigin.architecture.sample.cola.web.security.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.futureorigin.architecture.sample.cola.dto.command.user.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * UserManagementController
 * <p></p>
 *
 * @author Leander Lee on 2021/9/1.
 */
@RestController
@RequestMapping("management/user")
@Api(tags = "用户管理")
public class UserManagementController {

    private final UserService userService;

    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("add")
    @ApiOperation("添加用户")
    public Response add(@Valid @RequestBody UserAddCmd cmd) {
        SecurityUtils.bindUserContext(cmd);
        return userService.add(cmd);
    }

    @PostMapping("update")
    @ApiOperation("更新用户")
    public Response update(@Valid @RequestBody UserUpdateCmd cmd) {
        SecurityUtils.bindUserContext(cmd);
        return userService.update(cmd);
    }

    @PostMapping("list")
    @ApiOperation("查询用户列表")
    public PageResponse<UserListCO> list(@RequestBody UserListQry cmd) {
        return userService.list(cmd);
    }

    @GetMapping("{id}")
    @ApiOperation("查询用户信息")
    public SingleResponse<UserCO> get(@PathVariable("id") @NotNull Long id) {
        UserGetQry cmd = new UserGetQry();
        cmd.setId(id);
        return userService.get(cmd);
    }

    @PostMapping("resetPassword")
    @ApiOperation("重置密码")
    public Response resetPassword(@Valid @RequestBody UserPasswordResetCmd cmd) {
        SecurityUtils.bindUserContext(cmd);
        return userService.resetPassword(cmd);
    }

    @PostMapping("updateState")
    @ApiOperation("更新用户状态：禁用，启用")
    public Response updateState(@Valid @RequestBody UserStateUpdateCmd cmd) {
        SecurityUtils.bindUserContext(cmd);
        return userService.updateState(cmd);
    }

}
