package org.zerock.b01_2.security.Handler;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.zerock.b01_2.security.dto.MemberSecurityDTO;

import java.io.IOException;

// 소셜 로그인 이후 현재 사용자의 패스워드에 따라서 사용자 정보를 수정하거나 특정한 페이지로 이동하게 함

@Log4j2
@RequiredArgsConstructor
public class CustomSocialLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final PasswordEncoder passwordEncoder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        log.info("-------------------------------------");
        log.info("CustomLoginSuccessHanlder onAuthenticationSuccess");
        log.info(authentication.getPrincipal());

        MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();

        String encodedPW = memberSecurityDTO.getMpw();

        // 소셜 로그인이고 회원의 패스워드가 1111

        if (memberSecurityDTO.isSocial() && (memberSecurityDTO.getMpw().equals("1111")
                || passwordEncoder.matches("1111", memberSecurityDTO.getMpw()))) { // 자동 가입된 회원도 PasswordEncoder 를 이용해서
            // '1111'을 인코딩한 상태이므로 matches() 를 이용해서 검사하고 결과에 따라서 '/member/modify' 로 보내거나 '/board/list' 로 보낸다

            log.info("Should Change Password");

            log.info("Redirect to Member Modify");
            response.sendRedirect("/member/modify");

            return;

        } else {
            response.sendRedirect("/board/list");
        }
    }
}
