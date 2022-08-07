package com.xdl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 实现UserDetailsService接口
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("自定义登录逻辑");
        // 1、根据用户名去数据库查询，如果不存在则抛出UsernameNotFoundException异常
        if (!"admin".equals(username)) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 2、比较密码(注册时已经加密过)
        String password = passwordEncoder.encode("123");
        User user = new User(username, password,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,normal, ROLE_abc"));
        return user;
    }
}
