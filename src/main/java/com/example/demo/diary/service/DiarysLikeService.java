package com.example.demo.diary.service;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.diary.dao.DiaryLikeDao;
import com.example.demo.diary.dto.DiarysLike;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiarysLikeService {

  private final DiaryLikeDao diaryLikeDao;
  private static final AtomicLong dlidGenerator = new AtomicLong(System.currentTimeMillis());

   @Transactional
  public void addLike(Long did, Long mid) {
    if (!diaryLikeDao.exists(did, mid)) {
      DiarysLike like = new DiarysLike();
      like.setDlid(dlidGenerator.incrementAndGet());
      like.setDid(did);
      like.setMid(mid);
      diaryLikeDao.insert(like);
    }
  }

  @Transactional
  public void removeLike(Long did, Long mid) {
    diaryLikeDao.delete(did, mid);
  }

  // ✅ 추가: 총 좋아요 개수 반환
  public int countLikes(Long did) {
    return diaryLikeDao.countLikes(did);
  }

  // ✅ 추가: 현재 사용자가 좋아요 눌렀는지 여부
  public boolean isLikedByMe(Long did, Long mid) {
    return diaryLikeDao.exists(did, mid);
  }
}
