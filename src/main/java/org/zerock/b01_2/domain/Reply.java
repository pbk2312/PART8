package org.zerock.b01_2.domain;
//

import jakarta.persistence.*;
import lombok.*;


// 다대일 연관 관계의 구현
// Reply 클래스에는 Board 타입의 객체 참조를 board 라는 변수를 이용해서 참조하는데
// 이때 @ManyToOne 을 이용해서 '다대일' 관계로 구성됨을 설명합니다
@Entity
@Table(name = "Reply",indexes = {
        @Index(name = "idx_reply_board_bno",columnList = "board_bno")
})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String replyText;

    private String replyer;

    public void changeText(String text){
        this.replyText = text;
    }


}
