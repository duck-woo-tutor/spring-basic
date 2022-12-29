package io.security.config;

import io.security.config.security.CustomAuthenticationEntryPoint;
import io.security.config.security.CustomerAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurity {
    private final UserDetailsService userDetailsService;
    private final CustomerAccessDeniedHandler customerAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 설정
        http.csrf().disable();

        http.authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/api/user/**").permitAll()
                                .anyRequest().authenticated()
                )
                // Custom 로그인 페이지 사용
                .formLogin().loginPage("/api/user/login-page").permitAll();


        http.addFilterBefore(new CustomSecurityFilter(userDetailsService, passwordEncoder()), UsernamePasswordAuthenticationFilter.class);

        // 접근 제한 페이지 이동 설정
        // http.exceptionHandling().accessDeniedPage("/api/user/forbidden"); -> access

        http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);

        http.exceptionHandling().accessDeniedHandler(customerAccessDeniedHandler);

        return http.build();
    }
}
