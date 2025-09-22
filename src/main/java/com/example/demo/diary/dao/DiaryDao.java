package com.example.demo.diary.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.diary.dto.Diary;


/*
    "diaryId" NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "userId" NUMBER NOT NULL,
    "title" VARCHAR2(80 CHAR) NOT NULL,
    "content" CLOB NOT NULL,
    "visibility" VARCHAR2(10) DEFAULT 'PUBLIC' NOT NULL CHECK ("visibility" IN ('PUBLIC', 'FRIENDS', 'PRIVATE')),
    "createdAt" TIMESTAMP WITH TIME ZONE DEFAULT SYSTIMESTAMP NOT NULL,
    "updatedAt" TIMESTAMP WITH TIME ZONE DEFAULT SYSTIMESTAMP NOT NULL,
    "emo" VARCHAR2(10) NOT NULL CHECK ("emo" IN ('HAPPY', 'CALM', 'EXCITED', 'SAD')),
    "weather" VARCHAR2(10) NOT NULL CHECK ("weather" IN ('SUNNY', 'CLOUDY', 'RAINY', 'SNOWY')), 
    CONSTRAINT "fk_diary_user" FOREIGN KEY ("userId") REFERENCES "User"("userId") ON DELETE CASCADE
 */

@Mapper
public interface DiaryDao {
  public int insertDiary(com.example.demo.diary.dto.Diary diary);  // 일기 생성
  Diary selectLatestDiary(int mid);      // 가장 최근 글 조회(did가 db에서 자동생성 되므로 별도로 db에 들어간 글 보여주기)

  Diary selectDiaryById(int did);       // 단건 조회
  List<Diary> selectAllDiaries();       // 전체 조회
  
  int countDiaries();                                             // 페이징 처리 관련
  List<Diary> selectDiariesByPage(Map<String, Object> params);    // 페이징 처리 관련

  int updateDiary(Diary diary);         // 일기 수정
  int deleteDiary(int did);             // 일기 삭제
  
}
