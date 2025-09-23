package com.example.demo.guestboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.guestboard.dto.GuestBoard;
import com.example.demo.guestboard.dto.GuestBoardListRequest;
import com.example.demo.guestboard.dto.GuestBoardUpdateRequest;

@Mapper
public interface GuestBoardDao {
  public Long insert(GuestBoard guestBoard);
  public GuestBoard selectByGbid(Long guestBoardId);
  public List<GuestBoard> select(GuestBoardListRequest request);
  public Long update(GuestBoardUpdateRequest request);
  public Long delete(Long guestBoardId);
}
