package net.futureorigin.architecture.sample.cola.web.security.jwt;

import net.futureorigin.architecture.sample.cola.web.security.SecurityFilter;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * JWTConfigurer
 * <p></p>
 *
 * @author Leander Lee on 2021/9/11.
 */
public class JWTSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JWTProvider tokenProvider;

    public JWTSecurityConfigurer(JWTProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) {
        SecurityFilter customFilter = new SecurityFilter(tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
