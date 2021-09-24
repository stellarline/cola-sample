package net.futureorigin.architecture.sample.cola.dto.clientobject.credit;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.futureorigin.architecture.sample.cola.dto.clientobject.ClientObject;

import java.util.Date;

/**
 * CreditRecordListCO
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@Getter
@Setter
@ToString
public class CreditRecordListCO extends ClientObject {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private Date creationTime;
    private Long creator;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    private String userMobile;
    private String creatorName;

    /**
     * 积分数额
     */
    private Double credit;

    /**
     * 积分类型
     * 1：消费
     * 2：充值
     */
    private Integer type;
}
