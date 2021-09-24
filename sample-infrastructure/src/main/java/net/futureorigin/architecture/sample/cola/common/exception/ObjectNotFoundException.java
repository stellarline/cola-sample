package net.futureorigin.architecture.sample.cola.common.exception;

import com.alibaba.cola.exception.BizException;

/**
 * QueryException
 * <p></p>
 *
 * @author Leander Lee on 2021/9/2.
 */
public class ObjectNotFoundException extends BizException {

    public ObjectNotFoundException() {
        super(BizExceptionCode.OBJ_NOT_FOUND.getCode(), BizExceptionCode.OBJ_NOT_FOUND.getMessage());
    }

    public ObjectNotFoundException(String errMessage) {
        super(BizExceptionCode.OBJ_NOT_FOUND.getCode(), errMessage);
    }

    public ObjectNotFoundException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public ObjectNotFoundException(String errMessage, Throwable e) {
        super(errMessage, e);
    }

    public ObjectNotFoundException(String errorCode, String errMessage, Throwable e) {
        super(errorCode, errMessage, e);
    }
}
