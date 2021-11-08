package net.futureorigin.architecture.sample.cola.gatewayimpl.convertor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import net.futureorigin.architecture.sample.cola.domain.model.user.User;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.user.UserDO;

import java.util.ArrayList;
import java.util.List;

/**
 * UserConverter
 * <p></p>
 *
 * @author Leander Lee on 2021/9/2.
 */
public class UserConvertor {

    public static User toEntity(UserDO userDO) {
        return BeanUtil.toBean(userDO, User.class);
    }

    public static List<User> toEntityList(List<UserDO> userDOS) {
        if (CollectionUtil.isNotEmpty(userDOS)) {
            List<User> userList = new ArrayList<>();
            userDOS.forEach(userDO -> {
                userList.add(toEntity(userDO));
            });
            return userList;
        }
        return null;
    }

    public static UserDO toDO(User user) {
        return BeanUtil.toBean(user, UserDO.class);
    }
}
