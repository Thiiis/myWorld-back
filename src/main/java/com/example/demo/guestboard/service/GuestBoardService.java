package com.example.demo.guestboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.guestboard.dao.GuestBoardDao;
import com.example.demo.guestboard.dto.GuestBoard;
import com.example.demo.guestboard.dto.InfiniteScroll;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GuestBoardService {

  private final GuestBoardDao guestBoardDao;

  public GuestBoard create(GuestBoard guestBoard) {
    guestBoardDao.insert(guestBoard);
    return guestBoard;
  }

  public List<GuestBoard> getGuestBoardList(Long offset, Long limit) {
    InfiniteScroll scroll = new InfiniteScroll();
    scroll.setOffset(offset);
    scroll.setLimit(limit);
    return guestBoardDao.select(scroll);
  }

  public GuestBoard getGuestBoard(Long gbid) {
    GuestBoard guestBoard = guestBoardDao.selectByGbid(gbid);
    return guestBoard;
  }

  public Long update(GuestBoard guestBoard) {
    return guestBoardDao.update(guestBoard);
  }

  public Long delete(Long gbid) {
    Long rows = guestBoardDao.delete(gbid);
    return rows;
  }
} 
