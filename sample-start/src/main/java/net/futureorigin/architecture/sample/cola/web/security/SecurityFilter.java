package net.futureorigin.architecture.sample.cola.web.security;

import net.futureorigin.architecture.sample.cola.web.security.jwt.JWTProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * JWTConfigurer
 * <p>
 * Filters incoming requests and installs a Spring Security principal if a header corresponding to a valid user is
 * </p>
 *
 * @author Leander Lee on 2021/9/11.
 */
public class SecurityFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_HEADER_START = "Bearer ";
    private static final Logger LOG = LoggerFactory.getLogger(SecurityFilter.class);
    private JWTProvider tokenProvider;

    public SecurityFilter(JWTProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    public static String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(AUTHORIZATION_HEADER_START)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            LOG.debug("set Authentication to security context for '{}', uri: {}", authentication.getName(), requestURI);
        } else {
            LOG.debug("no valid JWT token found, uri: {}", requestURI);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
