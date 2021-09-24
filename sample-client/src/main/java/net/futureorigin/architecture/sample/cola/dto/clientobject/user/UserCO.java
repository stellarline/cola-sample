package net.futureorigin.architecture.sample.cola.dto.clientobject.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import net.futureorigin.architecture.sample.cola.dto.clientobject.ClientObject;

/**
 * UserCO
 * <p></p>
 *
 * @author Leander Lee on 2021/9/4.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserCO extends ClientObject {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    private String name;

    private String mobile;

    /**
     * 等级编码
     */
    private String gradePlainText;

    private Double creditBalance;

    private String userRoleCodes;

}
