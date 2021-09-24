package net.futureorigin.architecture.sample.cola.domain.model.user;

import com.alibaba.cola.domain.Entity;
import net.futureorigin.architecture.sample.cola.domain.model.BaseDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User
 * <p></p>
 *
 * @author Leander Lee create on 2021/8/30.
 */
@Getter
@Setter
@ToString
@Entity
public class User extends BaseDomain {
    public static final int STATE_ACTIVE = 0;
    public static final int STATE_INACTION = 1;
    public static final int STATE_DISABLED = 2;

    private String name;

    private String mobile;

    private String password;

    /**
     * 等级编码
     */
    private String gradeCode;

    /**
     * 用户状态
     * 0：活动
     * 1：非活动
     * 2：禁用
     */
    private Integer state = 0;
}
