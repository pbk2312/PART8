package org.zerock.b01_2.repository;
//
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.b01_2.domain.Reply;

// Reply 는 Board 와 별도로 CRUD 가 일어 날 수 있기때문에 별도의 Repository 를 작성해서 관리하도록 구성합니다.
// 게시물 삭제는 게시물을 사용하는 댓글들을 먼저 삭제해야함
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("select r from Reply r where r.board.bno = :bno")
    Page<Reply> listOfBoard(Long bno, Pageable pageable);


    void deleteByBoard_Bno(Long bno);
}
