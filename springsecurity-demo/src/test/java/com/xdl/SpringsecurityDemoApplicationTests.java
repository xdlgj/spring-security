package com.xdl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SpringsecurityDemoApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密每次加密后的结果不同
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
        // $2a$10$AgvVak0znTPd0AuOVb9TTe04XE.oN41NKd6yRUbI5LcaoCsjyZDYe
        // $2a$10$eOukO1OaJeAK5bniCXGEXuj5CTvyq/Bw0pzSedjn2mqKij8FpaWN.
        // 比较密码
        boolean matches = passwordEncoder.matches("123", encode);
        System.out.println(matches);

    }

}
