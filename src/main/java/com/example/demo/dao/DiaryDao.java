package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.diary.Diary;

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
  public int insertDiary(Diary diary);
  
}