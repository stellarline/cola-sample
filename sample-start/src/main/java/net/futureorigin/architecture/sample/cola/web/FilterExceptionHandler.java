package net.futureorigin.architecture.sample.cola.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "exception")
@ApiIgnore
public class FilterExceptionHandler {
    public static final String EXCEPTION_ATTR_NAME = "filterException";
    public static final String EXCEPTION_HANDLER_REQUEST = "/exception/handleFilterException";

    @RequestMapping(path = "handleFilterException")
    public void handleFilterException(HttpServletRequest request) throws Exception {
        throw (Exception) request.getAttribute(EXCEPTION_ATTR_NAME);
    }
}