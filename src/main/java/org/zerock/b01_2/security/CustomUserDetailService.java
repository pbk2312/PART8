package org.zerock.b01_2.security;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// UserDetails 는 사용자 인증과 관련된 정보들을 저장하는 역할을 합니다.

@Log4j2
@Service
public class CustomUserDetailService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    public CustomUserDetailService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        log.info("loadUserByUsername: " + username);


        // User 클래스는 빌더 방식을 지원함
        UserDetails userDetails = User.builder()
                .username("user1")
                .password(passwordEncoder.encode("1111")) // 패스워드 인코딩 필요
                .authorities("ROLE_USER")
                .build();


        return userDetails;


    }


}
