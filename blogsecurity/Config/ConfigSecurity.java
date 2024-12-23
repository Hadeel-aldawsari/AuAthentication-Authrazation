package com.example.blogsecurity.Config;

import com.example.blogsecurity.Service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {

    private final MyUserDetailsService myUserDetailsService;

    public ConfigSecurity(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
    DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
    daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
    return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
    http.csrf().disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .and()
        .authenticationProvider(daoAuthenticationProvider())
        .authorizeHttpRequests()
        .requestMatchers("/api/v1/blog-system/user/register").permitAll()
        .requestMatchers("/api/v1/blog-system/blog/user/**").hasAuthority("USER")
        .requestMatchers("/api/v1/blog-system/blog/admin/**").hasAuthority("ADMIN")
         .requestMatchers("/api/v1/blog-system/admin/**").hasAuthority("ADMIN")
        .anyRequest().authenticated()
        .and()
        .logout().logoutUrl("/api/v1/logout")
        .deleteCookies("JSESSIONID")
        .invalidateHttpSession(true)
        .and()
        .httpBasic();
    return http.build();
    }


}
