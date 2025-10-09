package com.example.demo.diary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.diary.dto.Diarys;
import com.example.demo.diary.dto.Pager;

@Mapper
public interface DiarysDao {
  void insert(Diarys diary);                       // 생성

  Diarys selectByDid(Long did);                    // 단건 조회

  int countDiariesByMid(Long mid);                             // 페이징 처리 관련(총 글 개수) ->전체 조회는 사용 x, 내용 전체 다 가져오는 것보다 개수로 가져오는 것이 성능향상에 도움됨
  List<Diarys> selectDiariesByPage(@Param("mid") Long mid, @Param("pager") Pager pager); // 페이징 처리 관련(총 페이지 수)

  int update(Diarys diary);                        // 수정

  int delete(Long did);                           // 단건 삭제
  int deleteDiaries(List<Long> dids);             // 다중 삭제
  
}
