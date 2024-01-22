package org.zerock.b01_2.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;


@SpringBootTest
@Log4j2
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*@Test
    public void insertMembers() {
    // 일반 회원 추가 테스트

        IntStream.rangeClosed(1, 100).forEach(i -> {

            Member member = Member.builder()
                    .mid("member" + i)
                    .mpw(passwordEncoder.encode("1111"))
                    .email("email" + i + "@aaa.bbb")
                    .build();

            member.addRole(MemberRole.USER);

            if (i >= 90) {
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);


        });


    }
     */

    /*@Test
    public void testRead() {
        // 회원 조회 테스트

        Optional<Member> result = memberRepository.getWithRoles("member100");

        Member member = result.orElseThrow();

        log.info(member);
        log.info(member.getRoleSet());

        member.getRoleSet().forEach(memberRole -> log.info(memberRole.name()));

    }
     */

    @Commit
    @Test
    public void testUpdate() {

        String mid = "pbk05255@daum.net"; // 소셜 로그인으로 추가된 사용자로 현재 DB에 존재하는 이메일
        String mpw = passwordEncoder.encode("54321"); // 비밀번호 변경

        memberRepository.updatePassword(mpw, mid);


    }


}





