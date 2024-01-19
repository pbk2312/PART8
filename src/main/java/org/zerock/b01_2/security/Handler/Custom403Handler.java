package org.zerock.b01_2.security.Handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;


import java.io.IOException;

// <form> 태그의 요청이 403인 경우 로그인 페이지로 이동할 때 'ACCESS_DENIED' 값을 파라미터로 같이 전달
// Ajax 인 경우에는 JSON 데이터를 전송

@Log4j2
public class Custom403Handler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        log.info("--------ACCESS DENIED--------------");

        response.setStatus(HttpStatus.FORBIDDEN.value());

        //JSON 요청이었는지 확인
        String contentType = request.getHeader("Content-Type");

        boolean jsonRequest = contentType.startsWith("application/json");

        log.info("isJOSN: " + jsonRequest);

        //일반 request
        if (!jsonRequest) {

            response.sendRedirect("/member/login?error=ACCESS_DENIED");
        }

    }
}