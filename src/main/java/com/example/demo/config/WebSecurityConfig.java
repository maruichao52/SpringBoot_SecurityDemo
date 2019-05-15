package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.server.UserServiceDetail;

@Configuration
@EnableWebSecurity
//通过@EnableWebSecurity注解开启Spring Security的功能
@EnableGlobalMethodSecurity(securedEnabled = true,jsr250Enabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private UserServiceDetail userServiceDetail;
	/**
	 * authorizeRequests()定义哪些URL需要被保护、哪些不需要被保护。
	 * 例如以上代码指定了/和/home不需要任何认证就可以访问，其他的路径都必须通过身份验证。
	 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()//不进行拦截
                .anyRequest().authenticated()
                .and()
            .formLogin()//通过formLogin()定义当需要用户登录时候，转到的登录页面。
                .loginPage("/login")
                .permitAll()//无条件允许访问
                .and()
            .logout()
                .permitAll();
    }
    
    //身份验证管理生成器
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	
    	auth.userDetailsService(userServiceDetail).passwordEncoder(passwordEncoder());
    }
    /***
	 * 密码加密方式
	 * 
	 * @Author MRC
	 * @Date 2019年4月24日 下午10:45:23
	 * @return
	 */
	@Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
	/***
	 * 无需检查的路径
	 */
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico");
    }
	
}