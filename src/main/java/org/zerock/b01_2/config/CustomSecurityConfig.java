package org.zerock.b01_2.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Log4j2
@RequiredArgsConstructor
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 로그인 하지 않아도 볼 수 있도록 설정

        // /login 으로 리다이엑트 X

        log.info("------configure--------");

        // http.formLogin(); 스프링 부트 버전 변경

        http.formLogin(form -> form.loginPage("/member/login")); // 로그인이 필요한 경우에 'member/login' 으로 자동 리다이렉트 되도록 함
        // POST 방식 처리 역시 같은 경로로 스프링 시큐리티 내부에서 처리됩니다.로그아웃 방식도 마찬가지로 POST 방식으로 처리되는 로그아웃
        // 역시 스프링 시큐리티가 처리하고 개발자가 간단하게 GET 방식으로 동작하는 로그아웃 화면을 구성하기만 하면 됩니다.

        // http.csrf().disable();
        http.csrf(csrf -> csrf.disable()); // 버전 변경 , CSRF 토큰 비활성화 -> username 과 password 라는 파라미터만으로 로그인 가능


        return http.build();

        //
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        // 정적으로 동작하는 파일들에는 굳이 시큐리티 적용 X
        log.info("---------web configure---------");

        return (web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()));


    }


}
