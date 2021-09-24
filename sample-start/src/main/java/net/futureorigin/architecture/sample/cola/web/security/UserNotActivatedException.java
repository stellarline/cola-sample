package net.futureorigin.architecture.sample.cola.web.security;

import org.springframework.security.core.AuthenticationException;

/**
 * UserNotActivatedException
 * <p>
 * </p>
 *
 * @author Leander Lee on 2021/9/11.
 */
public class UserNotActivatedException extends AuthenticationException {

    public UserNotActivatedException(String message) {
        super(message);
    }
}
