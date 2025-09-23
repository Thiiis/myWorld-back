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

@RequiredArgsConstructor
@Service
public class GuestBoardService {

  private final GuestBoardDao guestBoardDao;

  public GuestBoardCreateResponse create(GuestBoardCreateRequest request) {
    GuestBoard guestBoard = new GuestBoard(request);
    guestBoardDao.insert(guestBoard);
    return new GuestBoardCreateResponse(guestBoard);
  }

  public List<GuestBoardListResponse> getGuestBoardList(GuestBoardListRequest request) {
    List<GuestBoard> list = guestBoardDao.select(request);
    return list.stream().map(GuestBoardListResponse::new).toList();
  }

  public GuestBoard getGuestBoard(Long gbid) {
    GuestBoard guestBoard = guestBoardDao.selectByGbid(gbid);
    return guestBoard;
  }

  public Long update(GuestBoardUpdateRequest request) {
    return guestBoardDao.update(request);
  }

  public Long delete(Long gbid) {
    return guestBoardDao.delete(gbid);
  }
} 
