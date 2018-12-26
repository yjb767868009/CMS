package com.xmu.cms.config;

import com.xmu.cms.config.filter.JwtAuthenticationFilter;
import com.xmu.cms.config.filter.JwtLoginFilter;
import com.xmu.cms.config.handler.AuthenticationFailureHandler;
import com.xmu.cms.config.handler.AuthenticationSuccessHandler;
import com.xmu.cms.config.entrypoint.AdminAuthorizedEntryPoint;
import com.xmu.cms.config.entrypoint.UserAuthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
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
        private JwtAuthenticationProvider jwtAuthenticationProvider;

        @Autowired
        private AuthenticationSuccessHandler successHandler;

        @Autowired
        private AuthenticationFailureHandler failureHandler;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.exceptionHandling().
                    authenticationEntryPoint(new AdminAuthorizedEntryPoint());
            http.addFilter(authJwtLoginFilter())
                    .addFilterBefore(authJwtAuthenticationFilter(), JwtLoginFilter.class);
            http.authorizeRequests()
                    .antMatchers("/user/login", "/admin/login", "/user/password").permitAll()
                    .antMatchers("/**").permitAll()
                    .anyRequest().permitAll()
                    .anyRequest().authenticated();
            http.formLogin()
                    .loginPage("/admin/login").loginProcessingUrl("/admin/login")
                    .usernameParameter("account").passwordParameter("password")
                    .successHandler(successHandler).failureHandler(failureHandler)
                    .permitAll();
            http.csrf().disable();
            http.cors();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(jwtAuthenticationProvider);
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/resources/**");
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Bean
        public JwtLoginFilter authJwtLoginFilter() throws Exception {
            JwtLoginFilter loginFilter = new JwtLoginFilter();
            loginFilter.setAuthenticationManager(authenticationManager());
            return loginFilter;
        }

        @Bean
        public JwtAuthenticationFilter authJwtAuthenticationFilter() throws Exception {
            return new JwtAuthenticationFilter(authenticationManager());
        }
    }

    @Configuration
    public static class UserConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private JwtAuthenticationProvider jwtAuthenticationProvider;

        @Autowired
        private AuthenticationSuccessHandler successHandler;

        @Autowired
        private AuthenticationFailureHandler failureHandler;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.exceptionHandling().
                    authenticationEntryPoint(new UserAuthorizedEntryPoint());
            http.addFilter(userJWTLoginFilter())
                    .addFilterBefore(userJWTAuthenticationFilter(), JwtLoginFilter.class);
            http.formLogin()
                    .loginPage("/user/login").loginProcessingUrl("/user/login")
                    .usernameParameter("account").passwordParameter("password")
                    .successHandler(successHandler).failureHandler(failureHandler)
                    .permitAll();
            http.authorizeRequests()
                    .antMatchers("/user/login").permitAll()
                    .antMatchers("/test/**").permitAll()
                    .anyRequest().authenticated();
            http.csrf().disable();
            http.cors();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(jwtAuthenticationProvider);
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/resources/**");
        }

        @Bean
        public JwtLoginFilter userJWTLoginFilter() throws Exception {
            JwtLoginFilter loginFilter = new JwtLoginFilter();
            loginFilter.setAuthenticationManager(authenticationManager());
            return loginFilter;
        }

        @Bean
        public JwtAuthenticationFilter userJWTAuthenticationFilter() throws Exception {
            return new JwtAuthenticationFilter(authenticationManager());
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
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH","OPTIONS"));
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
