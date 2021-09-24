package net.futureorigin.architecture.sample.cola.dto.command.user;

import com.alibaba.cola.dto.PageQuery;
import lombok.*;

/**
 * UserListQryCmd
 * <p></p>
 *
 * @author Leander Lee on 2021/9/4.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserListQry extends PageQuery {

    private String mobile;

}
