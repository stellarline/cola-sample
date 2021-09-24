package net.futureorigin.architecture.sample.cola.command.user;

import cn.hutool.core.bean.BeanUtil;
import net.futureorigin.architecture.sample.cola.context.UserContext;
import net.futureorigin.architecture.sample.cola.domain.gateway.GradeCreditRuleGateway;
import net.futureorigin.architecture.sample.cola.domain.gateway.UserGateway;
import net.futureorigin.architecture.sample.cola.domain.model.gradecreditrule.GradeCreditRule;
import net.futureorigin.architecture.sample.cola.domain.model.user.User;
import net.futureorigin.architecture.sample.cola.dto.command.user.UserAddCmd;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * UserAddCmdExe
 * <p></p>
 *
 * @author Leander Lee on 2021/9/1.
 */
@Component
public class UserAddCmdExe extends BaseUserCmdExe {

    private final UserGateway userGateway;
    private final GradeCreditRuleGateway gradeCreditRuleGateway;

    private final PasswordEncoder passwordEncoder;

    public UserAddCmdExe(UserGateway userGateway,
                         GradeCreditRuleGateway gradeCreditRuleGateway,
                         UserMapper userMapper,
                         PasswordEncoder passwordEncoder) {
        super(userMapper);
        this.userGateway = userGateway;
        this.gradeCreditRuleGateway = gradeCreditRuleGateway;
        this.passwordEncoder = passwordEncoder;
    }

    public User execute(UserAddCmd cmd) {
        validateMobileFormat(cmd.getMobile());
        checkIsMobileExist(cmd.getMobile());

        User user = BeanUtil.toBean(cmd, User.class);
        // 用户名默认设置为手机号
        user.setName(cmd.getMobile());
        // encrypt password
        user.setPassword(passwordEncoder.encode(cmd.getPassword()));

        GradeCreditRule lowest = gradeCreditRuleGateway.getLowestGrade();
        user.setGradeCode(lowest.getCode());

        user.setCreationTime(new Date());
        UserContext userContext = cmd.getUserContext();
        user.setCreator(userContext.getLoggedInUserId());
        user.setModifier(userContext.getLoggedInUserId());
        user.setModifiedTime(new Date());

        return userGateway.add(user);
    }


}
