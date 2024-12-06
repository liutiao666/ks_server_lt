//package com.zmkj.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.sql.DataSource;
//
////@Configuration
////@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    public DataSource mysqlDataSource;
//
//    //授权
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/").permitAll()
//                .antMatchers("/level1").hasRole("vip1")
//                .antMatchers("/level2").hasRole("vip2")
//                .antMatchers("/level3").hasRole("vip3");
//
//        //没有权限会默认到登录界面，需要开启登录的界面
//        http.formLogin();
//        //开启注销
//        http.logout().logoutUrl("/");
//    }
//
//    /**
//     * 认证
//     *
//     * 直接写密码新版本会出现密码编码的问题
//     * 可以使用加密BCryptPasswordEncoder方法
//     *
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        /*auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("lt").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2")
//                .and()
//                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2", "vip3");*/
//        // 数据库
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        auth.jdbcAuthentication()
//                .dataSource(mysqlDataSource)
//                .withDefaultSchema()
//                .withUser(users.username("user").password("password").roles("USER"))
//                .withUser(users.username("admin").password("password").roles("admin"));
//
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
