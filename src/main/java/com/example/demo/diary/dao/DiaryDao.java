package com.example.demo.diary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.diary.dto.Diary;
import com.example.demo.diary.dto.Pager;
import com.example.demo.diary.dto.request.DiaryUpdateRequest;

@Mapper
public interface DiaryDao {
  void insertDiary(Diary diary);                    // 일기 생성
  Diary selectLatestDiary(Long mid);                // 가장 최근 글 조회(did가 db에서 자동생성 되므로 별도로 db에 들어간 글 보여주기)

  Diary selectDiaryById(Long did);                  // 단건 조회
  // List<Diary> selectAllDiaries();                // 전체 조회 -> 내용 전체 다 가져오는 것보다 개수로 가져오는 것이 성능향상에 도움됨
  int countDiaries();                               // 페이징 처리 관련(총 글 개수)
  List<Diary> selectDiariesByPage(Pager pager);     // 페이징 처리 관련(총 페이지 수)

  int updateDiary(DiaryUpdateRequest request);      // 일기 수정
  int deleteDiary(Long did);                        // 일기 삭제
  
}
