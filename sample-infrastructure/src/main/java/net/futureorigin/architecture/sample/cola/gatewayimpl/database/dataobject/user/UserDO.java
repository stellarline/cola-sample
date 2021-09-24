package net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.user;

import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * UserDO
 * <p></p>
 *
 * @author Leander Lee create on 2021/8/30.
 */
@Getter
@Setter
@ToString
@Table(name = "sample_user")
@Entity
public class UserDO extends BaseDO {

    @Column(nullable = false, length = 32)
    private String name;

    @Column(nullable = false, length = 11)
    private String mobile;

    @Column(nullable = false)
    private String password;

    /**
     * 等级编码
     */
    @Column(name = "grade_code", nullable = false, length = 16)
    private String gradeCode;

    /**
     * 用户状态
     * 0：活动
     * 1：非活动
     * 2：禁用
     */
    @Column(nullable = false)
    private Integer state = 0;

    @Transient
    private String gradePlainText;

    @Transient
    private Double creditBalance;

    @Transient
    private String userRoleCodes;


}
