package com.example.demo.diary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.diary.dto.DiaryComments;


@Mapper
public interface DiarysCommentDao {
  void insert(DiaryComments diaryComment);                                             // 생성
  DiaryComments selectByDcid(Long dcid);                                               // 
  DiaryComments selectByDidAndDcid(@Param("did") Long did, @Param("dcid") Long dcid);  //
  List<DiaryComments> selectByDid(@Param("did") Long did);
  int update(@Param("did") Long did, @Param("comment") DiaryComments comment);                // 수정
  int delete(@Param("did") Long did, @Param("dcid") Long dcid, @Param("mid") Long mid);                       // 삭제
}
