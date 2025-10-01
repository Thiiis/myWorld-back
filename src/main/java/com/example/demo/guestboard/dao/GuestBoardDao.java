package com.example.demo.guestboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.guestboard.dto.GuestBoard;

@Mapper
public interface GuestBoardDao {
  public int insert(GuestBoard guestBoard);
  public GuestBoard selectByGbid(Long guestBoardId);
  public List<GuestBoard> select(@Param("hostid") Long hostid, @Param("offset") Long offset, @Param("limit") Long limit);
  public int update(GuestBoard guestBoard);
  public int delete(Long guestBoardId);
}
