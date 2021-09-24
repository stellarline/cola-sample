package net.futureorigin.architecture.sample.cola.web.security.jwt;

import com.alibaba.cola.exception.BizException;
import net.futureorigin.architecture.sample.cola.common.exception.BizExceptionCode;
import net.futureorigin.architecture.sample.cola.web.FilterExceptionHandler;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JwtAuthenticationEntryPoint
 * <p></p>
 *
 * @author Leander Lee on 2021/9/11.
 */
@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
        // Here you can place any message you want

        request.setAttribute(FilterExceptionHandler.EXCEPTION_ATTR_NAME,
                new BizException(BizExceptionCode.AUTH_FAILURE.getCode(), BizExceptionCode.AUTH_FAILURE.getMessage()));
        //将异常分发到 /exception/filterException 控制器
        request.getRequestDispatcher(FilterExceptionHandler.EXCEPTION_HANDLER_REQUEST).forward(request, response);
    }
}
