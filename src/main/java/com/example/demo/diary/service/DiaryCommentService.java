// package com.example.demo.diary.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import com.example.demo.diary.dao.DiaryCommentDao;
// import com.example.demo.diary.dto.DiaryComment;
// import com.example.demo.diary.dto.request.DiaryCommentRequest;
// import com.example.demo.diary.dto.response.DiaryCommentResponse;
// import com.example.demo.diary.dto.response.DiaryResponse;

// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @Service
// public class DiaryCommentService {

//   @Autowired
//   private DiaryCommentDao diaryCommentDao;

//   // 댓글 생성
//   @Transactional
//   public DiaryCommentResponse createDiaryComment(Long did, DiaryCommentRequest dto) {
//     //객체생성(entity DTO에 값 주입)
//     DiaryComment comment = diaryCommentDao.insert(comment);
    
//     //responseDTO에 단건조회(DB에 값을 클라이언트에 보내기 위함) 값 담기
//     DiaryCommentResponse savedComment = diaryCommentDao.selectByDcid(comment.getDcid());

//     return savedComment;
//   }

//   // 댓글 조회
//   public DiaryCommentResponse getDiaryComment(Long did, Long dcid) {
//     //조회(보안을 위해 did와 dcid를 모두 사용하여 조회-어떤 id값인지 모름)
//     DiaryComment comment = diaryCommentDao.selectByDidAndDcid(did, dcid);

//     return new DiaryCommentReadResponse(comment.getDcid(), comment.getDid(), comment.getMid(), comment.getParentDcid(), comment.getContent(), comment.getCreateAt(), comment.getUpdateAt());
//   }

//   // 댓글 수정
//   @Transactional
//   public void updateDiaryComment(Long did, Long dcid, DiaryCommentUpdateRequest dto) {
//     // [보안 로직 추가 예정]: 실제 사용자의 mid를 확인하여 수정 권한이 있는지 검증해야 합니다.

//     //객체에 담기
//     // dcid와 content만 사용하여 수정용 DiaryComment 객체 생성 
// 		DiaryComment comment = new DiaryComment( did, dcid, dto.getContent());
		
// 		// DAO 호출 시, did와 comment 객체를 모두 전달합니다.
// 		int updated = diaryCommentDao.update(did, comment);
		
// 		if (updated == 0) {
//       throw new IllegalArgumentException("수정할 댓글을 찾을 수 없습니다. (DID 또는 DCID 불일치)"); // 댓글이 존재하지 않거나, 해당 DID에 속하지 않아 수정에 실패한 경우
//     }
//   }

//   // 댓글 삭제
//   @Transactional
//   public void deleteDiaryComment(Long did, Long dcid) {
//     diaryCommentDao.delete(did, dcid);
//   }
// }
