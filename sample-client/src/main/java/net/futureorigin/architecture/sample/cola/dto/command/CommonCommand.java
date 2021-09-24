package net.futureorigin.architecture.sample.cola.dto.command;

import com.alibaba.cola.dto.Command;
import com.fasterxml.jackson.annotation.JsonIgnore;
import net.futureorigin.architecture.sample.cola.context.UserContext;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * BaseCommand
 * <p></p>
 *
 * @author Leander Lee on 2021/9/11.
 */
@Getter
@Setter
@ToString
public abstract class CommonCommand extends Command {

    @JsonIgnore
    protected UserContext userContext;

}
