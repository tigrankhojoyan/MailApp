package com.test.mail.app.web.security;

import javax.sql.DataSource;

import com.test.mail.app.business.services.impl.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author tigrank
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/api/usermvc/user").authenticated()
//                .anyRequest().authenticated()
//                .antMatchers("/api/a")
//                .access("hasRole('ROLE_ADMIN')")
                .and().formLogin()
                .loginPage("/api/usermvc/login")
                .defaultSuccessUrl("/api/usermvc/user")
                .usernameParameter("userName")
                .passwordParameter("password")
                .failureUrl("/api/usermvc/login?error")
                .and().logout().logoutSuccessUrl("/api/usermvc/login?logout")
                .and().csrf().disable()
                .exceptionHandling().accessDeniedPage("/403");
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new CustomPasswordEncoder();
        return encoder;
    }
}