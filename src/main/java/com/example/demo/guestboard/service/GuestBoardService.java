package com.example.demo.guestboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.guestboard.dao.GuestBoardDao;
import com.example.demo.guestboard.dto.GuestBoard;
import com.example.demo.guestboard.dto.GuestBoardCreateRequest;
import com.example.demo.guestboard.dto.GuestBoardCreateResponse;
import com.example.demo.guestboard.dto.GuestBoardListRequest;
import com.example.demo.guestboard.dto.GuestBoardListResponse;
import com.example.demo.guestboard.dto.GuestBoardUpdateRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuestBoardService {

  private final GuestBoardDao guestBoardDao;

  // board create
  public GuestBoardCreateResponse create(GuestBoardCreateRequest request) {
    GuestBoard guestBoard = new GuestBoard(request.getGid(), request.getPid(), request.getContent(), request.getViewScope());
    guestBoardDao.insert(guestBoard);
    GuestBoard dbGuestBoard = guestBoardDao.selectByGbid(guestBoard.getGbid());
    return new GuestBoardCreateResponse(dbGuestBoard.getGbid(), dbGuestBoard.getCreatedAt(), dbGuestBoard.getUpdatedAt());
  }

  // board list
  public List<GuestBoardListResponse> getGuestBoardList(GuestBoardListRequest request) {
    List<GuestBoard> list = guestBoardDao.select(request.getOffset(), request.getLimit());
    return list.stream().map(g -> new GuestBoardListResponse(g.getGbid(), g.getGid(), g.getContent(), g.getViewScope(), g.getCreatedAt(), g.getUpdatedAt())).toList();
  }

  public GuestBoard getGuestBoard(Long gbid) {
    GuestBoard guestBoard = guestBoardDao.selectByGbid(gbid);
    return guestBoard;
  }

  // board update
  public Long update(GuestBoardUpdateRequest request) {
    GuestBoard guestBoard = new GuestBoard(request.getGbid(), request.getContent(), request.getViewScope());
    return guestBoardDao.update(guestBoard);
  }

  public Long delete(Long gbid) {
    return guestBoardDao.delete(gbid);
  }
} 
