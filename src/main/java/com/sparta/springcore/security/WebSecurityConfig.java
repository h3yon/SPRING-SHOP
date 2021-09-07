package com.sparta.springcore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        // 암호화해줌. Bean으로 다른 곳에서 사용 가능하도록 함.
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeRequests()
                // image 폴더를 login 없이 허용
                .antMatchers("/images/**").permitAll()
                // css 폴더를 login 없이 허용
                .antMatchers("/css/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                // 그 외 모든 요청은 로그인 인증과정 필요, 와 css파일도 필요해서 에러.
                .anyRequest().authenticated()
                .and()
                // 로그인 페이지에게는 허용
                .formLogin()
                // 로그인할 때 페이지
                .loginPage("/user/login")
                .loginProcessingUrl("/user/login")
                // 로그인 완료 후 이동
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                // 로그아웃 기능도 허용
                .logout()
                .permitAll();
    }


}