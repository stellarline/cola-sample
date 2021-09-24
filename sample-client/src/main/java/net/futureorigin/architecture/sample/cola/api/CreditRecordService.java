package net.futureorigin.architecture.sample.cola.api;

import com.alibaba.cola.dto.PageResponse;
import net.futureorigin.architecture.sample.cola.dto.clientobject.credit.CreditRecordListCO;
import net.futureorigin.architecture.sample.cola.dto.command.credit.CreditRecordListQry;

/**
 * CreditRecordService
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
public interface CreditRecordService {

    PageResponse<CreditRecordListCO> listAll(CreditRecordListQry cmd);

}
