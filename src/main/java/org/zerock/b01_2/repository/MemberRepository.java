package org.zerock.b01_2.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b01_2.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.mid = :mid and m.social = false")
    Optional<Member> getWithRoles(String mid);


    @EntityGraph(attributePaths = "roleSet")
    Optional<Member> findByEmail(String email);


    // 소셜 로그인으로 로그인하면 무조건 패스워드가 '1111'을 인코딩한 값으로 저장되므로 패스워드가 변경된 상황에서의 테스트가 불가하여 이를 위해 패스워드를 수정할 수 있는 기능 추가
    @Modifying
    @Transactional
    @Query("update Member m set m.mpw =:mpw where m.mid = :mid ")
    void updatePassword(@Param("mpw") String password, @Param("mid") String mid);

}