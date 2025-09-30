// package com.example.demo.diary.dao;

// import org.apache.ibatis.annotations.Mapper;
// import org.apache.ibatis.annotations.Param;

// import com.example.demo.diary.dto.DiaryComment;

// @Mapper
// public interface DiaryCommentDao {
//   void insert(DiaryComment diaryComment);                                             // 생성
//   DiaryComment selectByDcid(Long dcid);                                               // 
//   DiaryComment selectByDidAndDcid(@Param("did") Long did, @Param("dcid") Long dcid);  //
//   int update(@Param("did") Long did, @Param("comment") DiaryComment comment);         // 수정
//   int delete(@Param("did") Long did, @Param("dcid") Long dcid);                       // 삭제
// }
