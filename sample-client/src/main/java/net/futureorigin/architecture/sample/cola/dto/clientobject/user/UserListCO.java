package net.futureorigin.architecture.sample.cola.dto.clientobject.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import net.futureorigin.architecture.sample.cola.dto.clientobject.ClientObject;

import java.util.Date;

/**
 * UserCO
 * <p></p>
 *
 * @author Leander Lee create on 2021/9/3.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserListCO extends ClientObject {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    private Date creationTime;

    private Date modifiedTime;

    private String name;

    private String mobile;

    /**
     * 用户状态
     * 0：活动
     * 1：非活动
     * 2：禁用
     */
    private Integer state = 0;

    /**
     * 等级编码
     */
    private String gradePlainText;

    private Double creditBalance;

    private String userRoleCodes;
}
