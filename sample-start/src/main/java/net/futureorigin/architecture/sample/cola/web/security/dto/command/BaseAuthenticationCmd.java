package net.futureorigin.architecture.sample.cola.web.security.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.*;

/**
 * BaseAuthenticationCmd
 * <p></p>
 *
 * @author Leander Lee on 2021/9/11.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseAuthenticationCmd extends Command {

    private Boolean rememberMe;
}
