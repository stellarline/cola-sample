package net.futureorigin.architecture.sample.cola.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import lombok.extern.slf4j.Slf4j;
import net.futureorigin.architecture.sample.cola.api.CreditRecordService;
import net.futureorigin.architecture.sample.cola.command.credit.CreditRecordListQryExe;
import net.futureorigin.architecture.sample.cola.dto.clientobject.credit.CreditRecordListCO;
import net.futureorigin.architecture.sample.cola.dto.command.credit.CreditRecordListQry;
import org.springframework.stereotype.Service;

/**
 * CreditRecordServiceImpl
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@Service
@Slf4j
@CatchAndLog
public class CreditRecordServiceImpl extends BaseService implements CreditRecordService {

    private final CreditRecordListQryExe listQryCmdExe;

    public CreditRecordServiceImpl(CreditRecordListQryExe listQryCmdExe) {
        this.listQryCmdExe = listQryCmdExe;
    }

    @Override
    public PageResponse<CreditRecordListCO> listAll(CreditRecordListQry cmd) {
        try {
            return listQryCmdExe.execute(cmd);
        } catch (Exception e) {
            log.error("查询积分记录列表失败 : ", e);
            Response response = getErrorResponse(e);
            return PageResponse.buildFailure(response.getErrCode(), response.getErrMessage());
        }
    }
}
