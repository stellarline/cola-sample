package net.futureorigin.architecture.sample.cola.dto.command.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.futureorigin.architecture.sample.cola.dto.command.CommonCommand;

/**
 * UserRole
 * <p></p>
 *
 * @author Leander Lee on 2021/9/11.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserRoleAddCmd extends CommonCommand {

    private Long userId;

    private String roleCode;
}
