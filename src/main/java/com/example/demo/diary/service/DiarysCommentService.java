package com.example.demo.diary.service;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.diary.dao.DiarysCommentDao;
import com.example.demo.diary.dto.DiaryComments;
import com.example.demo.diary.dto.request.DiarysCommentRequest;
import com.example.demo.diary.dto.response.DiarysCommentResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiarysCommentService {

  private final DiarysCommentDao diaryCommentDao;

  // 댓글 생성
  @Transactional
  public DiarysCommentResponse createDiaryComment(Long did, Long mid, DiarysCommentRequest dto) {
    // 객체생성(entity DTO에 값 주입)
    DiaryComments comment = new DiaryComments();
    comment.setDid(did);
    comment.setMid(mid);
    comment.setContent(dto.getContent());
    // dao에 전달
    diaryCommentDao.insert(comment);
    // responseDTO에 단건조회(DB에 값을 클라이언트에 보내기 위함) 값 담기
    DiaryComments response = diaryCommentDao.selectByDcid(comment.getDcid());
    boolean mine = response.getMid().equals(mid);
    return new DiarysCommentResponse(response.getDcid(), response.getDid(), mine, response.getContent(),
        response.getCreatedAt(), response.getUpdatedAt());
  }

  // 댓글 조회
  public DiarysCommentResponse getDiaryComment(Long did, Long dcid, Long mid) {
    // 조회(보안을 위해 did와 dcid를 모두 사용하여 조회-어떤 id값인지 모름)
    DiaryComments response = diaryCommentDao.selectByDidAndDcid(did, dcid);
    boolean mine = response.getMid().equals(mid);
    return new DiarysCommentResponse(response.getDcid(), response.getDid(), mine, response.getContent(),
        response.getCreatedAt(), response.getUpdatedAt());
  }

  // 댓글 리스트 조회
  public List<DiarysCommentResponse> getCommentsByDid(Long did, long mid) {
    List<DiaryComments> response = diaryCommentDao.selectByDid(did);

    return response.stream()
        .map(rc -> new DiarysCommentResponse(rc.getDcid(), rc.getDid(), rc.getMid().equals(mid), rc.getContent(),
            rc.getCreatedAt(), rc.getUpdatedAt()))
        .collect(Collectors.toList());
  }

  // 댓글 수정
  @Transactional
  public DiarysCommentResponse updateDiaryComment(Long did, Long mid, Long dcid, DiarysCommentRequest dto) {
    String content = dto.getContent();

    // 수정용 엔티티 구성
    DiaryComments comment = new DiaryComments();
    comment.setDid(did);
    comment.setDcid(dcid);
    comment.setMid(mid);
    comment.setContent(content);

    int updated = diaryCommentDao.update(did, comment);
    if (updated == 0) {
      throw new IllegalArgumentException("수정할 댓글을 찾을 수 없습니다. (DID 또는 DCID 불일치)");
    }

    // ✅ 수정된 결과 다시 조회해서 DTO로 변환
    DiaryComments response = diaryCommentDao.selectByDcid(dcid);
    boolean mine = response.getMid().equals(mid);

    // ✅ 순서 주의 (dcid → did 순)
    return new DiarysCommentResponse(
        response.getDcid(),
        response.getDid(),
        mine,
        response.getContent(),
        response.getCreatedAt(),
        response.getUpdatedAt());
  }

  // 댓글 삭제
  @Transactional
  public void deleteDiaryComment(Long did, Long mid, Long dcid) {
    int deleted = diaryCommentDao.delete(did, dcid, mid); // ✅ did, dcid, mid 모두 조건
    if (deleted == 0) {
      throw new IllegalArgumentException("삭제할 댓글을 찾을 수 없거나 권한이 없습니다. (DID/DCID 불일치 또는 본인 댓글 아님)");
    }
    ;
  }
}