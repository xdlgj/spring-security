package com.xdl.config;

import com.xdl.handler.MyAccessDeniedHandler;
import com.xdl.handler.MyAuthenticationFailureHandler;
import com.xdl.handler.MyAuthenticationSuccessHandler;
import com.xdl.service.MyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAccessDeniedHandler accessDenied;
    @Autowired
    private MyServiceImpl myServiceImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //自定义入参
                .passwordParameter("pwd")
                // 自定义登录也页面
                .loginPage("/login.html")
                //必须和表单提交的接口一样，会去执行自定义登录逻辑
                .loginProcessingUrl("/login")
                // 登录成功后跳转的页面只支持POST请求
                //.successForwardUrl("/toMain")
                // 自定义登录处理器
                .successHandler(new MyAuthenticationSuccessHandler("main.html"))
                // 登录失败
                //.failureForwardUrl("/toError")
                // 自定义失败处理器
                .failureHandler(new MyAuthenticationFailureHandler("/error.html"));


        // 授权
        http.authorizeRequests()
                // 放行login.html，不需要认证
                .antMatchers("/login.html").permitAll()
                //.antMatchers("/error.html").permitAll()
                .antMatchers("/error.html").access("permitAll")
                // 放行静态资源
                //.antMatchers("/css/**", "/js/**", "/images/**").permitAll()
                //放行后缀jpg图片
                //.antMatchers("/**/*.jpg").permitAll()
                // 放行后缀jpg图片 []表示转义
                .regexMatchers(".+[.]jpg").permitAll()
                // 指定请求方法
                //.regexMatchers(HttpMethod.POST,"/demo").permitAll()
                //.regexMatchers("/xxx/demo").permitAll()
                //.mvcMatchers("/demo").servletPath("/xxx").permitAll()
                //权限控制, 严格区分大小写,User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,normal"));
                //.antMatchers("/main1.html").hasAuthority("admin")
                //只要满足一个权限即可访问
                //.antMatchers("/main1.html").hasAnyAuthority("admin", "adminN")
                // 根据角色进行权限控制，AuthorityUtils.commaSeparatedStringToAuthorityList("admin,normal, ROLE_abc"));
                //.antMatchers("/main1.html").hasRole("abc")
                .antMatchers("/main1.html").access("hasRole('abc')")
                //.antMatchers("/main1.html").hasAnyRole("abc", "aa")
                //基于IP地址进行权限控制
                .antMatchers("/main1.html").hasIpAddress("127.0.0.1")
                // 所有的请求都需要授权，必须放在最后面
                //.anyRequest().authenticated();
                // 自定以的access方法
                .anyRequest().access("@myServiceImpl.hasPermission(request, authentication)");
        // 异常处理
        http.exceptionHandling()
                .accessDeniedHandler(accessDenied);
        // 关闭csrf
        http.csrf().disable();
    }

    /**
     * 将BCryptPasswordEncoder对象添加到Spring IOC 容器中,对象名称为passwordEncoder
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
