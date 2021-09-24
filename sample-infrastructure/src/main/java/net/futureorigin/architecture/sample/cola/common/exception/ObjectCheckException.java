package net.futureorigin.architecture.sample.cola.common.exception;

import com.alibaba.cola.exception.BizException;

/**
 * ObjectCheckException
 * <p></p>
 *
 * @author Leander Lee create on 2021/9/3.
 */
public class ObjectCheckException extends BizException {

    public ObjectCheckException() {
        super(BizExceptionCode.OBJ_CHECK_FAILURE.getCode(), BizExceptionCode.OBJ_CHECK_FAILURE.getMessage());
    }

    public ObjectCheckException(String errMessage) {
        super(BizExceptionCode.OBJ_CHECK_FAILURE.getCode(), errMessage);
    }

    public ObjectCheckException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public ObjectCheckException(String errMessage, Throwable e) {
        super(errMessage, e);
    }

    public ObjectCheckException(String errorCode, String errMessage, Throwable e) {
        super(errorCode, errMessage, e);
    }
}
