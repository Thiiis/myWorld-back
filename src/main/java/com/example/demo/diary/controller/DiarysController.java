package com.example.demo.diary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.auth.service.MemberService;
import com.example.demo.diary.dto.Pager;
import com.example.demo.diary.dto.request.DiarysRequest;
import com.example.demo.diary.dto.response.DiarysResponse;
import com.example.demo.diary.service.DiarysService;
import com.example.demo.interceptor.Login;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/diaries")
@AllArgsConstructor
public class DiarysController {

  private final DiarysService diaryService;
  private final MemberService memberService;

  // 일기 생성
  @Login
  @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<DiarysResponse> createDiary(
      @RequestPart("dto") DiarysRequest dto,
      @RequestPart(value = "files", required = false) List<MultipartFile> files, @RequestParam(value = "hostAccount") String hostAccount,
      HttpServletRequest request) {

    Long loginMid = (Long) request.getAttribute("loginMid");
    Long hostMid = memberService.getMember(hostAccount).getMid();

    if(!loginMid.equals(hostMid)) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "다른 사용자의 일기장에는 작성할 수 없습니다.");
    }

    // 1) 일기 저장 + DB insert
    DiarysResponse diaryResponse = diaryService.createDiary(loginMid, dto, files);
    return ResponseEntity.status(HttpStatus.CREATED).body(diaryResponse);
  }

  // 일기 상세조회
  @Login
  @GetMapping("/list/detail")
  public ResponseEntity<DiarysResponse> getDiary(@RequestParam("did") Long did, HttpServletRequest request) {
    Long loginMid = (Long) request.getAttribute("loginMid");
    DiarysResponse diary = diaryService.getDiary(loginMid, did);
    return ResponseEntity.ok(diary);
  }

  // 일기 페이지리스트 조회
  @Login
  @GetMapping("/list")
  public ResponseEntity<Map<String, Object>> getDiariesPage(
      @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value ="hostAccount", required = false) String hostAccount, HttpServletRequest request) {
        
    Long loginMid = (Long) request.getAttribute("loginMid");   //로그인한 회원

    Long hostMid = (hostAccount != null) ? memberService.getMember(hostAccount).getMid() : loginMid; // 로그인 여부와 별개로 등록되어있는 회원

    int totalRows = diaryService.countDiaries(hostMid);
    Pager pager = new Pager(6, 5, totalRows, pageNo);

    List<DiarysResponse> responses = diaryService.getDiariesPage(hostMid, pager);

    Map<String, Object> result = new HashMap<>();
    result.put("diaries", responses); // 현재 페이지 데이터
    result.put("totalItems", totalRows); // 전체 개수
    result.put("totalPages", pager.getTotalPageNo()); // 전체 페이지 수
    result.put("currentPage", pageNo); // 현재 페이지 번호

    return ResponseEntity.ok(result);
  }

  // 일기 수정
  @Login
  @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Void> updateDiary(
      @RequestPart("dto") DiarysRequest dto,
      @RequestParam(value = "deleteAids", required = false) List<Long> deleteAids,
      @RequestPart(value = "addFiles", required = false) List<MultipartFile> addFiles,
      @RequestParam(value = "hostAccount") String hostAccount,
      HttpServletRequest request) {

    Long loginMid = (Long) request.getAttribute("loginMid");
    Long hostMid = memberService.getMember(hostAccount).getMid();

    if(!loginMid.equals(hostMid)) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "다른 사용자의 일기장에는 작성할 수 없습니다.");
    }
    diaryService.updateDiary(loginMid, dto, deleteAids, addFiles);
    return ResponseEntity.noContent().build();
  }

  // 일기 단일 삭제
  @Login
  @DeleteMapping("/delete/{did}")
  public ResponseEntity<Void> deleteDiary(
      @PathVariable("did") Long did,
      @RequestParam(value = "hostAccount") String hostAccount,
      HttpServletRequest request) {

    Long loginMid = (Long) request.getAttribute("loginMid");
    Long hostMid = memberService.getMember(hostAccount).getMid();

    if(!loginMid.equals(hostMid)) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "다른 사용자의 일기장에는 작성할 수 없습니다.");
    }
    diaryService.deleteDiary(loginMid, did);
    return ResponseEntity.noContent().build();
  }

  // 일기 다중 삭제 (여러 아이디를 받을 때에는 쿼리 파라미터를 사용하는 것이 일반적)
  @Login
  @DeleteMapping("/delete-list")
  public ResponseEntity<Void> deleteDiaries(
      @RequestParam("did") List<Long> dids,
      @RequestParam(value = "hostAccount") String hostAccount,
      HttpServletRequest request) {

    Long loginMid = (Long) request.getAttribute("loginMid");
    Long hostMid = memberService.getMember(hostAccount).getMid();

    if(!loginMid.equals(hostMid)) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "다른 사용자의 일기장에는 작성할 수 없습니다.");
    }
    
    diaryService.deleteDiaries(dids);
    return ResponseEntity.noContent().build();
  }
}
