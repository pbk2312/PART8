package org.zerock.b01_2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.b01_2.domain.Member;
import org.zerock.b01_2.domain.MemberRole;
import org.zerock.b01_2.dto.MemberJoinDTO;
import org.zerock.b01_2.repository.MemberRepository;
//
@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final ModelMapper modelMapper;

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void join(MemberJoinDTO memberJoinDTO) throws MidExistException{


        String mid = memberJoinDTO.getMid();

        boolean exist = memberRepository.existsById(mid); // mid 값이 유일한지 체크


        if(exist){
            throw new MidExistException();
        }   // 문제가 발생하면 예외정


        Member member = modelMapper.map(memberJoinDTO, Member.class);
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        member.addRole(MemberRole.USER);

        log.info("=======================");
        log.info(member);
        log.info(member.getRoleSet());

        memberRepository.save(member);



    }



}
