package net.futureorigin.architecture.sample.cola.web.security.jwt;

import com.alibaba.cola.exception.BizException;
import net.futureorigin.architecture.sample.cola.common.exception.BizExceptionCode;
import net.futureorigin.architecture.sample.cola.web.FilterExceptionHandler;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWTAccessDeniedHandler
 * <p></p>
 *
 * @author Leander Lee on 2021/9/11.
 */
@Component
public class JWTAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // This is invoked when user tries to access a secured REST resource without the necessary authorization
        // We should just send a 403 Forbidden response because there is no 'error' page to redirect to
        // Here you can place any message you want

        request.setAttribute(FilterExceptionHandler.EXCEPTION_ATTR_NAME,
                new BizException(BizExceptionCode.ACCESS_FORBIDDEN.getCode(), BizExceptionCode.ACCESS_FORBIDDEN.getMessage()));
        //将异常分发到 /exception/filterException 控制器
        request.getRequestDispatcher(FilterExceptionHandler.EXCEPTION_HANDLER_REQUEST).forward(request, response);
    }
}
