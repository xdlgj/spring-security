package com.xdl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    /**
     * 将BCryptPasswordEncoder对象添加到Spring IOC 容器中
     * @return
     */
    @Bean
    public PasswordEncoder getPwd() {
        return new BCryptPasswordEncoder();
    }
}
