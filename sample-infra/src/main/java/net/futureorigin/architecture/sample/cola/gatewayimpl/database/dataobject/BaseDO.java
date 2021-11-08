package net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * BaseDO
 * <p></p>
 *
 * @author Leander Lee on 2021/8/31.
 */
@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class BaseDO {

    @Id
    @Column
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator",
            strategy = "net.futureorigin.architecture.sample.cola.config.CustomIdentifierGenerator"
    )
    private Long id;

    /**
     * 0：有效
     * 1：逻辑删除
     */
    @Column(nullable = false)
    private Integer deleted = 0;

    @Column(nullable = false)
    private Integer version = 0;

    @Column(name = "creation_time", nullable = false)
    private Date creationTime;

    @Column(nullable = false)
    private Long creator;

    @Column(name = "modified_time", nullable = false)
    private Date modifiedTime;

    @Column(nullable = false)
    private Long modifier;
}
