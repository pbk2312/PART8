package org.zerock.b01_2.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zerock.b01_2.dto.PageRequestDTO;
import org.zerock.b01_2.dto.PageResponseDTO;
import org.zerock.b01_2.dto.ReplyDTO;
import org.zerock.b01_2.service.ReplyService;

import java.util.HashMap;
import java.util.Map;

@RestController // 리턴값 자체가 JSON 으로 처리
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor // 의존성 주입을 위한
public class ReplyController {


    private final ReplyService replyService;


    // @RequestBody ReplyDTO replyDTO -> JSON 문자열을 ReplyDTO로 변환하기 위해 사용
    // consumes 는 해당 메소드를 받아서 소비(consumes)하는 데이터가 어떤 종류인지 명시
    @Operation(summary = "Replies POST", description = "POST 방식으로 댓글 등록")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE) // JSON 타입의 데이터를 처리하는 메소드임을 명시
    public Map<String, Long> register(@Valid @RequestBody ReplyDTO replyDTO,
                                      BindingResult bindingResult) throws BindException {

        log.info(replyDTO);

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        Map<String, Long> resultMap = new HashMap<>();

        Long rno = replyService.register(replyDTO);

        resultMap.put("rno", rno);

        return resultMap;


    }

    // 댓글 목록

    @Operation(summary = "Replies of Board", description = "GET 방식으로 특정 게시물의 댓글 목록")
    @GetMapping(value = "/list/{bno}")
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("bno") Long bno, PageRequestDTO pageRequestDTO) {

        PageResponseDTO<ReplyDTO> responseDTO = replyService.getListOfBoard(bno, pageRequestDTO);

        return responseDTO;

        // bno 값은 경로에 있는 값을 취해서 사용할 것이므로 @PathVariable 을 이용하고,페이지와 관련된 정보는
        // 일반 쿼리 스트링을 이용합니다


    }

    // 댓글 조회
    @Operation(summary = "Read Reply", description = "GET 방식으로 특정 댓글 조회")
    @GetMapping("/{rno}")
    public ReplyDTO getReplyDTO(@PathVariable("rno") Long rno) {

        ReplyDTO replyDTO = replyService.read(rno);

        return replyDTO;

    }

    @Operation(summary = "Delete Reply", description = "DELETE 방식으로 특정한 댓글 삭제")
    @DeleteMapping("/{rno}")
    public Map<String, Long> remove(@PathVariable("rno") Long rno) {

        replyService.remove(rno);

        Map<String, Long> resultMap = new HashMap<>();

        resultMap.put("rno", rno);

        return resultMap;

    }

    @Operation(summary = "Modify Reply", description = "PUT 방식으로 특정 댓글 수정")
    @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> remove(@PathVariable("rno") Long rno, @RequestBody ReplyDTO replyDTO) {


        replyDTO.setRno(rno); // 번호 일치 시킴

        replyService.modify(replyDTO);

        Map<String, Long> resultMap = new HashMap<>();

        resultMap.put("rno", rno);

        return resultMap;


    }

}
