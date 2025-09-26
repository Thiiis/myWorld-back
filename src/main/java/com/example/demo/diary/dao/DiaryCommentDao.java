package com.example.demo.diary.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.diary.dto.DiaryComment;

@Mapper
public interface DiaryCommentDao {
  void insert(DiaryComment diaryComment);
  DiaryComment selectByDcid(Long dcid);
  DiaryComment selectByDidAndDcid(@Param("did") Long did, @Param("dcid") Long dcid);
  int update(@Param("dcid") Long dcid, @Param("did") Long did, DiaryComment dto);
}
