package com.example.springbootdemo.config;

import com.example.springbootdemo.Domain.Authority;
import com.example.springbootdemo.Domain.User;
import com.example.springbootdemo.infrastructure.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;

import static org.springframework.security.core.userdetails.User.withUsername;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    final UserRepository userRepository;

    public SecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .mvcMatchers(HttpMethod.POST, "/users").permitAll()
                .mvcMatchers(HttpMethod.GET, "/users").hasRole("admin")
                .mvcMatchers(HttpMethod.POST, "/cars").hasRole("admin")
                .anyRequest().authenticated()
                .and().httpBasic();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            System.out.println("===========" + username);
            User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
            System.out.println("========" + user);
            UserDetails userDetails = withUsername(user.getUsername())
                    .password(user.getPassword())
                    .authorities(user.getAuthorityList().stream().map(Authority::getAuthority).map(SimpleGrantedAuthority::new).collect(Collectors.toList()))
                    .disabled(!user.getEnabled())
                    .build();
            System.out.println("=========" + userDetails);
            return userDetails;
        };

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
