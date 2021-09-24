package net.futureorigin.architecture.sample.cola.dto.command.credit;

import com.alibaba.cola.dto.PageQuery;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

/**
 * CreditRecordListQryCmd
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreditRecordListQry extends PageQuery {

    @JsonIgnore
    private Long userId;

    private String userMobile;
}
