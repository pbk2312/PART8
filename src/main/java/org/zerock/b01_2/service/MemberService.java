package org.zerock.b01_2.service;

import org.zerock.b01_2.dto.MemberJoinDTO;

// 회원 가입에서 신경써야 하는 것은 이미 해당 아이디가 존재하는 경우 MemberRepository 의 save()는 insert 가 아니라 update 로 실행됩니다.만일 같은 아이디가 존재하면 예외를 발생하도록 처리합니다.
public interface MemberService {

    static class MidExistException extends Exception {

    }

    void join(MemberJoinDTO memberJoinDTO) throws MidExistException;


}
