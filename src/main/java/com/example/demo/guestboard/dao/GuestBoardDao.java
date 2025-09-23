package com.example.demo.guestboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.guestboard.dto.GuestBoard;

@Mapper
public interface GuestBoardDao {
  public Long insert(GuestBoard guestBoard);
  public GuestBoard selectByGbid(Long guestBoardId);
  public List<GuestBoard> select(@Param("offset") Long offset, @Param("limit") Long limit);
  public Long update(GuestBoard guestBoard);
  public Long delete(Long guestBoardId);
}
