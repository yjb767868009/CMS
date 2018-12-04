package com.xmu.cms.config;

import com.xmu.cms.handler.AuthenticationFailureHandler;
import com.xmu.cms.handler.AuthenticationSuccessHandler;
import com.xmu.cms.service.Impl.AuthUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * @author JuboYu on 2018/12/4.
 * @version 1.0
 */
@EnableWebSecurity
public class MultiHttpSecurityConfiguration {

    @Configuration
    @Order(1)
    public static class AdminConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private AuthUserServiceImpl authUserService;

        @Autowired
        private AuthenticationSuccessHandler authenticationSuccessHandler;

        @Autowired
        private AuthenticationFailureHandler authenticationFailureHandler;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/admin/**")
                    .authorizeRequests()
                    .anyRequest().hasRole("ADMIN");
            http.formLogin()
                    .loginPage("/api/admin/login").loginProcessingUrl("/api/admin/login")
                    .usernameParameter("account").passwordParameter("password")
                    .successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler)
                    .permitAll();
            http.csrf().disable();
            http.cors();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(authUserService).passwordEncoder(passwordEncoder());
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/resources/**");
        }

    }

    @Configuration
    public static class UserConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private AuthUserServiceImpl authUserService;

        @Autowired
        private AuthenticationSuccessHandler authenticationSuccessHandler;

        @Autowired
        private AuthenticationFailureHandler authenticationFailureHandler;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/**")
                    .authorizeRequests()
                    .antMatchers("/api/test/**").permitAll()
                    .antMatchers("/api/user/**").hasAnyRole("TEACHER", "STUDENT");
            http.formLogin()
                    .loginPage("/api/user/login").loginProcessingUrl("/api/user/login")
                    .usernameParameter("account").passwordParameter("password")
                    .successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler)
                    .permitAll();
            http.csrf().disable();
            http.cors();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(authUserService).passwordEncoder(passwordEncoder());
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/resources/**");
        }
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public static CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
