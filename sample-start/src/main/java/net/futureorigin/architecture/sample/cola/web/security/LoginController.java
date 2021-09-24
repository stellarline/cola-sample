package net.futureorigin.architecture.sample.cola.web.security;

import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.futureorigin.architecture.sample.cola.api.UserService;
import net.futureorigin.architecture.sample.cola.dto.clientobject.user.UserCO;
import net.futureorigin.architecture.sample.cola.dto.command.user.UserGetQry;
import net.futureorigin.architecture.sample.cola.web.security.dto.clientobject.AuthorizedCO;
import net.futureorigin.architecture.sample.cola.web.security.dto.command.MobileAuthenticationCmd;
import net.futureorigin.architecture.sample.cola.web.security.jwt.JWTProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * LoginController
 * <p>
 * Controller to authenticate users.
 * </p>
 *
 * @author Leander Lee on 2021/9/11.
 */
@RestController
@Api(tags = "登录认证")
public class LoginController {

    private final JWTProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserService userService;

    public LoginController(JWTProvider tokenProvider,
                           AuthenticationManagerBuilder authenticationManagerBuilder,
                           UserService userService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userService = userService;
    }

    @PostMapping("login")
    @ApiOperation("用户登录认证")
    public SingleResponse<AuthorizedCO> login(HttpServletResponse response, @Valid @RequestBody MobileAuthenticationCmd authenticationCmd) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authenticationCmd.getMobile(), authenticationCmd.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        boolean rememberMe = authenticationCmd.getRememberMe() != null && authenticationCmd.getRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);

        response.setHeader(SecurityFilter.AUTHORIZATION_HEADER, SecurityFilter.AUTHORIZATION_HEADER_START + jwt);
        return SingleResponse.of(new AuthorizedCO(jwt, getAuthorizedUser(authentication)));
    }

    private UserCO getAuthorizedUser(Authentication authentication) {
        UserGetQry userGetQry = new UserGetQry();
        userGetQry.setId(Long.valueOf(authentication.getName()));
        return userService.get(userGetQry).getData();
    }

}
