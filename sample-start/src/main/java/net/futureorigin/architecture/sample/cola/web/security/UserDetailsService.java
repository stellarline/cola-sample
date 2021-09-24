package net.futureorigin.architecture.sample.cola.web.security;

import cn.hutool.core.collection.CollectionUtil;
import net.futureorigin.architecture.sample.cola.domain.model.user.User;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.role.UserRoleDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.user.UserDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper.UserMapper;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper.UserRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/**
 * UserDetailsService
 * <p>
 * Authenticate a user from the database.
 * </p>
 *
 * @author Leander Lee on 2021/9/11.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;

    public UserDetailsService(UserMapper userMapper,
                              UserRoleMapper userRoleMapper) {
        this.userMapper = userMapper;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        if (log.isDebugEnabled()) {
            log.debug("Authenticating user '{}'", login);
        }
        UserDO userDO = userMapper.findByMobile(login);
        if (null == userDO) {
            throw new UserAuthenticationException("登录用户 [" + login + "] 未找到。");
        }
        List<UserRoleDO> userRoleDOS = userRoleMapper.find(userDO.getId());
        if (CollectionUtil.isEmpty(userRoleDOS)) {
            throw new UserAuthenticationException("登录用户 [" + login + "] 没有配置相应的角色，请联系管理员。");
        }
        return createSpringSecurityUser(userDO, userRoleDOS);
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(UserDO userDO, List<UserRoleDO> userRoleDOS) {
        if (userDO.getState() != User.STATE_ACTIVE) {
            throw new UserNotActivatedException("登录用户 [" + userDO.getMobile() + "] 处于非活动状态，无法登录。");
        }
        List<GrantedAuthority> grantedAuthorities = userRoleDOS.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRoleCodes()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(
                String.valueOf(userDO.getId()),
                userDO.getPassword(),
                grantedAuthorities
        );
    }
}
