package com.study.sys.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

/**
 * @author wxl
 * @date 2020/4/2 15:46:40
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("#{dataSource}")
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select user_name, password, status from sys_user " +
                                "where username=?")
                .authoritiesByUsernameQuery(
//                        select f.permission_id from sys_dep_permission f where
//                        f.department_id=(select c.department_id FROM sys_user_company c where c.user_id=(select a.id from sys_user a where a.user_name='wxl') limit 1)
                        "select a.user_name, from sys_user a " +
                                "left join sys_user_company b on b.user_id=a.id " +
                                "left join sys_dep_permission c on " +
                                "where username=?")
                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 不需要登录就可以访问
                .antMatchers("/login","/login")
                .permitAll()
                // 需要具有ROLE_USER角色才能访问
                .antMatchers("/user/**").hasAnyRole("USER")
                // 需要具有ROLE_ADMIN角色才能访问
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                // 设置登录页面
                .loginPage("/login")
                .loginProcessingUrl("/authentication/form")
                // 设置默认登录成功后跳转的页面
                .defaultSuccessUrl("/")
        ;
    }

}
