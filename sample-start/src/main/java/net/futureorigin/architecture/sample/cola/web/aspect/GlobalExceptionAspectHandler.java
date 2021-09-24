package net.futureorigin.architecture.sample.cola.web.aspect;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import net.futureorigin.architecture.sample.cola.common.exception.BizExceptionCode;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * GlobalExceptionAspectHandler
 * <p></p>
 *
 * @author Leander Lee on 2021/8/22.
 */
@RestControllerAdvice
@Order(999)
public class GlobalExceptionAspectHandler {

    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        String errorCode = BizExceptionCode.BIZ_FAILURE.getCode();
        if (e instanceof BizException) {
            BizException authException = (BizException) e;
            errorCode = authException.getErrCode();
        }

        return Response.buildFailure(errorCode, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        StringBuffer errorBuffer = new StringBuffer();
        List<ObjectError> objectErrors = result.getAllErrors();
        if (CollectionUtil.isNotEmpty(objectErrors)) {
            objectErrors.forEach(objectError -> {
                if (objectError instanceof FieldError) {
                    FieldError fieldError = (FieldError) objectError;
                    errorBuffer
                            .append("[")
                            .append(fieldError.getField())
                            .append("]")
                            .append(objectError.getDefaultMessage()).append(";");
                }
            });
        }

        return Response.buildFailure(
                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                StrUtil.isNotBlank(errorBuffer.toString()) ? errorBuffer.toString() : e.getMessage()
        );
    }

}
