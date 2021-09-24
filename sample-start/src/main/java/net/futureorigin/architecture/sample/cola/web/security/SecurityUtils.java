package net.futureorigin.architecture.sample.cola.web.security;

import lombok.extern.slf4j.Slf4j;
import net.futureorigin.architecture.sample.cola.context.UserContext;
import net.futureorigin.architecture.sample.cola.dto.command.CommonCommand;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.HashSet;
import java.util.Set;

/**
 * SecurityUtils
 * <p></p>
 *
 * @author Leander Lee create on 2021/9/13.
 */
@Slf4j
public class SecurityUtils {

    public static void bindUserContext(CommonCommand command) {
        command.setUserContext(getUserContext());
    }

    public static UserContext getUserContext() {
        return createUserContext(getSecurityUser());
    }

    private static UserContext createUserContext(User securityUser) {
        if (null == securityUser) {
            if (log.isWarnEnabled()) {
                log.warn("Security user not found.");
            }
            return null;
        }

        UserContext userContext = new UserContext();
        userContext.setLoggedInUserId(Long.valueOf(securityUser.getUsername()));

        Set<String> userRoles = new HashSet<>();
        securityUser.getAuthorities().forEach(grantedAuthority -> {
            userRoles.add(grantedAuthority.getAuthority());
        });
        userContext.setLoggedInUserRoles(userRoles);

        return userContext;
    }

    private static User getSecurityUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null == authentication) {
            if (log.isWarnEnabled()) {
                log.warn("Authentication is invalid or not found");
            }
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        }
        return null;
    }
}
