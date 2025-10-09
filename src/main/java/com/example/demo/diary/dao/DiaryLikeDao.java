package com.example.demo.diary.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.diary.dto.DiarysLike;

@Mapper
public interface DiaryLikeDao {

  void insert(DiarysLike like);
  void delete(@Param("did") Long did, @Param("mid") Long mid);
  boolean exists(@Param("did") Long did, @Param("mid") Long mid);
  int countLikes(@Param("did") Long did);
}
