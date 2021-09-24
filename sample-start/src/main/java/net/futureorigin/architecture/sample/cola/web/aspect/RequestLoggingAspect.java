package net.futureorigin.architecture.sample.cola.web.aspect;

import cn.hutool.core.date.DateUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.futureorigin.architecture.sample.cola.common.util.JsonUtils;
import net.futureorigin.architecture.sample.cola.context.UserContext;
import net.futureorigin.architecture.sample.cola.web.security.SecurityUtils;
import net.futureorigin.architecture.sample.cola.web.util.HostAddressUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
@Aspect
@Component
public class RequestLoggingAspect {

    @Pointcut("@within(org.springframework.stereotype.Controller)||" +
            "@within(org.springframework.web.bind.annotation.RestController)")
    public void controller() {
    }

    @Around("controller()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 请求开始时间
        long startTimeMillis = System.currentTimeMillis();
        Object result = null;
        String errMsg = null;
        try {
            // 执行请求
            result = joinPoint.proceed();
        } catch (Throwable e) {
            errMsg = e.toString();
            throw e;
        } finally {
            // 记录日志
            this.saveLog(joinPoint, startTimeMillis, result, errMsg);
        }
        return result;
    }

    /**
     * 记录日志
     *
     * @param joinPoint
     * @param startTimeMillis
     * @param result
     */
    private void saveLog(JoinPoint joinPoint, long startTimeMillis, Object result, String errMsg) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            // 请求日志
            RequestLog logInfo = new RequestLog();
            logInfo.setLoginIp(HostAddressUtils.getHost(request));
            logInfo.setServerName(request.getServerName());
            logInfo.setServerPort(request.getServerPort() + "");
            logInfo.setRequestUri(request.getRequestURI());
            logInfo.setRequestQueryString(request.getQueryString());
            logInfo.setRequestTime(DateUtil.format(new Date(startTimeMillis), "yyyy-MM-dd HH:mm:ss"));
            logInfo.setSpendTimeMillis(System.currentTimeMillis() - startTimeMillis);
            logInfo.setErrMsg(errMsg);

            if (!isQuery(request)) {
                logInfo.setResponse(result);
            }
            this.setUser(logInfo);
            this.setBody(logInfo, joinPoint, request);
            if (log.isInfoEnabled()) {
                log.info(JsonUtils.toJsonString(logInfo));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void setUser(RequestLog log) {
        UserContext userContext = SecurityUtils.getUserContext();
        if (null == userContext) {
            return;
        }
        log.setLoggedInUserId(String.valueOf(userContext.getLoggedInUserId()));
    }

    public void setBody(RequestLog log, JoinPoint joinPoint, HttpServletRequest request) {
        if (!"POST".equals(request.getMethod())) {
            return;
        }
        Object[] args = joinPoint.getArgs();
        if (args.length == 0) {
            return;
        }

        // 文件过滤
        if (ServletFileUpload.isMultipartContent(request)) {
            return;
        }

        for (Object o : args) {
            if (o instanceof HttpServletRequest || o instanceof HttpServletResponse) {
                continue;
            }
            log.setRequestPostData(o);
        }
    }

    private boolean isQuery(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String[] pathArr = requestURI.split("/");
        if (pathArr.length == 0) {
            return false;
        }
        String method = pathArr[pathArr.length - 1];
        return method.matches("(get|query|find|select|list).*");
    }

    @Data
    public static class RequestLog {
        /**
         * 操作员id
         */
        private String loggedInUserId;

        /**
         * 操作员手机号
         */
        private String mobile;

        /**
         * 登录IP
         */
        private String loginIp;

        /**
         * 登录城市
         */
        private String loginCity;

        /**
         * 服务器地址
         */
        private String serverName;

        /**
         * 服务器端口
         */
        private String serverPort;

        /**
         * 请求uri
         */
        private String requestUri;

        /**
         * 请求URL中参数
         */
        private String requestQueryString;

        /**
         * post数据
         */
        private Object requestPostData;

        /**
         * response数据
         */
        private Object response;

        /**
         * 请求时间
         */
        private String requestTime;

        /**
         * 耗时/毫秒
         */
        private Long spendTimeMillis;

        /**
         * 请求功能名称
         */
        private String resourceName;

        /**
         * 应用服务code
         */
        private String appCode;

        /**
         * 异常信息
         */
        private String errMsg;

    }

}
