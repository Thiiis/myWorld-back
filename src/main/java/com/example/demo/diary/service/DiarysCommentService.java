package com.example.demo.diary.service;

import java.net.http.HttpRequest;

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
    //객체생성(entity DTO에 값 주입)
    DiaryComments comment = new DiaryComments();
    comment.setDid(did);
    comment.setMid(mid);
    comment.setContent(dto.getContent());
    //dao에 전달
    diaryCommentDao.insert(comment);
    //responseDTO에 단건조회(DB에 값을 클라이언트에 보내기 위함) 값 담기
    DiaryComments response = diaryCommentDao.selectByDcid(comment.getDcid());
    return new DiarysCommentResponse(response.getDid(), response.getDcid(), response.getMid(), response.getContent(),response.getCreateAt(), response.getUpdateAt());
  }

  // 댓글 조회
  public DiarysCommentResponse getDiaryComment(Long did, Long dcid) {
    //조회(보안을 위해 did와 dcid를 모두 사용하여 조회-어떤 id값인지 모름)
    DiaryComments response = diaryCommentDao.selectByDidAndDcid(did, dcid);

    return new DiarysCommentResponse(response.getDid(), response.getDcid(), response.getMid(), response.getContent(), response.getCreateAt(), response.getUpdateAt());
  }

  // 댓글 수정
  @Transactional
  public void updateDiaryComment(Long did, Long mid, Long dcid, DiarysCommentRequest dto) {
    // [보안 로직 추가 예정]: 실제 사용자의 mid를 확인하여 수정 권한이 있는지 검증해야 합니다.

    //객체에 담기 // dcid와 content만 사용하여 수정용 DiaryComment 객체 생성 
		DiaryComments comment = new DiaryComments(did, mid, dcid, dto.getContent());
		
		// DAO 호출 시, did와 comment 객체를 모두 전달합니다.
		int updated = diaryCommentDao.update(did,comment);
		
		if (updated == 0) {
      throw new IllegalArgumentException("수정할 댓글을 찾을 수 없습니다. (DID 또는 DCID 불일치)"); // 댓글이 존재하지 않거나, 해당 DID에 속하지 않아 수정에 실패한 경우
    }
  }

  // 댓글 삭제
  @Transactional
  public void deleteDiaryComment(Long did, Long mid, Long dcid) {
    diaryCommentDao.delete(did, dcid);
  }
}
