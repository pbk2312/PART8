package org.zerock.b01_2.service;

import org.zerock.b01_2.dto.PageRequestDTO;
import org.zerock.b01_2.dto.PageResponseDTO;
import org.zerock.b01_2.dto.ReplyDTO;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    ReplyDTO read(Long rno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);

}
