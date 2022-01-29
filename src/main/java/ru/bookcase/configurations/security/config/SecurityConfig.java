package ru.bookcase.configurations.security.config;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.bookcase.entity.Role;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EnableWebSecurity
@Configuration
@ComponentScan(basePackages = "ru.bookcase.configurations.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/registration").permitAll()
                .antMatchers("/bookcase/book").hasAuthority(Role.USER.name())
                .antMatchers("/bookcase/book/all").hasAuthority(Role.USER.name())
                .antMatchers("/bookcase/book/download/{id}").hasAuthority(Role.USER.name())
                .and()
                .formLogin()
                .loginPage("/")
                .usernameParameter("login")
                .defaultSuccessUrl("/bookcase/book")
                .failureUrl("/bookcase/?error")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/bookcase/")
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
