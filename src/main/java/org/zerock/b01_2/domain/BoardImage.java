package org.zerock.b01_2.domain;


// 첨부파일을 의미하는 엔티티 클래스
// @ManyToOne 연관 관계를 적용합니다.첨부파일의 고유한 uuid 값과 파일의 이름,순번을 지정하고 @ManyToOne으로 Board 객체 지정
// Comparable 사용 이유는 순번에 맞게 정렬하기 위해서
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class BoardImage implements Comparable<BoardImage> {

    @Id
    private String uuid;

    private String fileName;

    private int ord;

    @ManyToOne
    private Board board;


    @Override
    public int compareTo(BoardImage other) {
        return this.ord - other.ord;
    }

    public void changeBoard(Board board){
        this.board = board;
    }

}