package org.zerock.b01_2.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.b01_2.domain.Board;
import org.zerock.b01_2.dto.BoardListAllDTO;
import org.zerock.b01_2.dto.BoardListReplyCountDTO;
import org.zerock.b01_2.dto.PageRequestDTO;
import org.zerock.b01_2.dto.PageResponseDTO;

public interface BoardSearch {

    Page<Board> search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types,
                                                      String keyword,
                                                      Pageable pageable);

    Page<BoardListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable);

    // 게시글의 이미지와 댓글의 숫자까지 처리
    PageResponseDTO<BoardListAllDTO> listWithAll(PageRequestDTO pageRequestDTO);


}
