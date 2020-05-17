package ru.example.securityapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/")
                        .permitAll()
                    .antMatchers(HttpMethod.POST, "/api/employees/**", "api/resources/**", "api/accountings/**")
                        .hasRole("FULL-ACCESS")
                    .antMatchers(HttpMethod.PUT, "api/employees/**", "api/resources/**", "api/accountings/**")
                        .hasRole("FULL-ACCESS")
                    .antMatchers(HttpMethod.DELETE, "api/employees/**", "api/resources/**", "/api/accountings/**")
                        .hasRole("FULL-ACCESS")
//                    .antMatchers("/employees/new/**", "/employees/update/**", "/employees/delete/**")
//                        .hasRole("FULL-ACCESS")
//                    .antMatchers("/resources/new/**", "/resources/update/**", "/resources/delete/**")
//                        .hasRole("FULL-ACCESS")
//                    .antMatchers("/accountings/new/**", "/accountings/update/**", "/accountings/delete/**")
//                        .hasRole("FULL-ACCESS")
                    .anyRequest()
                        .authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .and()
                .httpBasic()
                    .and()
                .csrf()
                    .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password(encoder.encode("password"))
                .roles("READ-ONLY")
                .and()
                .withUser("admin")
                .password(encoder.encode("admin"))
                .roles("FULL-ACCESS");
    }

}
