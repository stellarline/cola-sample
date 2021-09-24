package net.futureorigin.architecture.sample.cola.context;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

/**
 * UserContext
 * <p></p>
 *
 * @author Leander Lee on 2021/9/11.
 */
@Getter
@Setter
@ToString
public class UserContext {

    private Long loggedInUserId;

    private Set<String> loggedInUserRoles;

}
