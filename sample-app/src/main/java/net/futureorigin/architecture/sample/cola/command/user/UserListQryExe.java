package net.futureorigin.architecture.sample.cola.command.user;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.cola.dto.PageResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.futureorigin.architecture.sample.cola.dto.clientobject.user.UserListCO;
import net.futureorigin.architecture.sample.cola.dto.command.user.UserListQry;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.user.UserDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * UserQryCmdExe
 * <p></p>
 *
 * @author Leander Lee create on 2021/9/3.
 */
@Component
public class UserListQryExe {

    private final UserMapper userMapper;

    public UserListQryExe(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public PageResponse<UserListCO> execute(UserListQry cmd) {
        PageHelper.startPage(cmd.getPageIndex(), cmd.getPageSize(), true);

        UserDO query = new UserDO();
        query.setMobile(cmd.getMobile());
        Page<UserDO> userDOPage = userMapper.findBySelective(query);
        if (CollectionUtil.isEmpty(userDOPage)) {
            return PageResponse.buildSuccess();
        }
        List<UserDO> userDOList = userDOPage.getResult();
        List<UserListCO> userListCOS = new ArrayList<>();
        userDOList.forEach(user -> {
            userListCOS.add(BeanUtil.toBean(user, UserListCO.class));
        });

        return PageResponse.of(userListCOS,
                Math.toIntExact(userDOPage.getTotal()), userDOPage.getPageSize(), userDOPage.getPageNum());
    }
}
