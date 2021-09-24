package net.futureorigin.architecture.sample.cola.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * BaseDomain
 * <p></p>
 *
 * @author Leander Lee on 2021/9/1.
 */
@Getter
@Setter
@ToString
public abstract class BaseDomain {

    private Long id;

    /**
     * 0：有效
     * 1：逻辑删除
     */
    private Integer deleted = 0;

    private Integer version = 0;

    private Date creationTime;

    private Long creator;

    private Date modifiedTime;

    private Long modifier;
}
