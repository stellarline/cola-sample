package net.futureorigin.architecture.sample.cola.web.config;

import net.futureorigin.architecture.sample.cola.domain.model.role.UserRole;
import net.futureorigin.architecture.sample.cola.web.security.jwt.JWTAccessDeniedHandler;
import net.futureorigin.architecture.sample.cola.web.security.jwt.JWTAuthenticationEntryPoint;
import net.futureorigin.architecture.sample.cola.web.security.jwt.JWTProvider;
import net.futureorigin.architecture.sample.cola.web.security.jwt.JWTSecurityConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * WebSecurityConfig
 * <p></p>
 *
 * @author Leander Lee on 2021/9/11.
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JWTAuthenticationEntryPoint authenticationErrorHandler;
    private final JWTAccessDeniedHandler jwtAccessDeniedHandler;

    public WebSecurityConfig(
            JWTProvider tokenProvider,
            CorsFilter corsFilter,
            JWTAuthenticationEntryPoint authenticationErrorHandler,
            JWTAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.authenticationErrorHandler = authenticationErrorHandler;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    // Configure BCrypt password encoder =====================================================================

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configure paths and requests that should be ignored by Spring Security ================================

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")

                // allow anonymous resource requests
                .antMatchers("/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/doc.html",
                        "/v2/api-docs-ext",
                        "/swagger-resources",
                        "/v2/api-docs",
                        "/swagger-ui.html",
                        "/swagger-resources/configuration/ui",
                        "/swagger-resources/configuration/security",
                        "/index.html"
                );
    }

    // Configure security settings ===========================================================================

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()

                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling()
                .authenticationEntryPoint(authenticationErrorHandler)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // create no session
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()

                .antMatchers("/management/**").hasAuthority(UserRole.ROLE_ADMIN)
                .antMatchers("/member/**").hasAuthority(UserRole.ROLE_USER)

                .anyRequest().authenticated()

                .and()
                .apply(securityConfigurerAdapter());
    }

    private JWTSecurityConfigurer securityConfigurerAdapter() {
        return new JWTSecurityConfigurer(tokenProvider);
    }
}
