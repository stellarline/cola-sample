package net.futureorigin.architecture.sample.cola.service;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import net.futureorigin.architecture.sample.cola.common.exception.BizExceptionCode;

/**
 * AbstractService
 * <p></p>
 *
 * @author Leander Lee on 2021/9/2.
 */
public abstract class BaseService {

    protected Response getErrorResponse(Exception e) {
        String errCode = BizExceptionCode.BIZ_FAILURE.getCode();
        String errMessage = e.getMessage();
        if (e instanceof BizException) {
            errCode = ((BizException) e).getErrCode();
        }
        return Response.buildFailure(errCode, errMessage);
    }
}
