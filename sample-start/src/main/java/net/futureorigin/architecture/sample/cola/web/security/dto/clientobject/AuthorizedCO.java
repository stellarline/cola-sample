package net.futureorigin.architecture.sample.cola.web.security.dto.clientobject;

import net.futureorigin.architecture.sample.cola.dto.clientobject.user.UserCO;
import lombok.*;

import java.io.Serializable;

/**
 * AuthorizedCO
 * <p></p>
 *
 * @author Leander Lee on 2021/9/11.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizedCO implements Serializable {

    private String accessToken;

    private UserCO loggedInUser;
}
