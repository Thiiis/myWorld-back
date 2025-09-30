// package com.example.demo.diary.dao;

// import java.util.List;

// import org.apache.ibatis.annotations.Mapper;

// import com.example.demo.diary.dto.Diary;
// import com.example.demo.diary.dto.Pager;

// @Mapper
// public interface DiaryDao {
//   void insert(Diary diary);                       // 생성

//   Diary selectByDid(Long did);                    // 단건 조회
//   int countDiaries();                             // 페이징 처리 관련(총 글 개수) ->전체 조회는 사용 x, 내용 전체 다 가져오는 것보다 개수로 가져오는 것이 성능향상에 도움됨
//   List<Diary> selectDiariesByPage(Pager pager);   // 페이징 처리 관련(총 페이지 수)

//   int update(Diary diary);                        // 수정

//   int delete(Long did);                           // 단건 삭제
//   int deleteDiaries(List<Long> dids);             // 다중 삭제
  
// }
