package com.example.demo.diary.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.diary.dto.Diary;

@Mapper
public interface DiaryDao {
    public int insert(Diary diary);
}
