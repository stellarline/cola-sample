package net.futureorigin.architecture.sample.cola.web.security;

import org.springframework.security.core.AuthenticationException;

/**
 * UserAuthenticationException
 * <p>
 * </p>
 *
 * @author Leander Lee on 2021/9/11.
 */
public class UserAuthenticationException extends AuthenticationException {

    public UserAuthenticationException(String message) {
        super(message);
    }
}
