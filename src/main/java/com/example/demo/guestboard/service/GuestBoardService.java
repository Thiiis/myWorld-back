package com.example.demo.guestboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.guestboard.dao.GuestBoardDao;
import com.example.demo.guestboard.dto.GuestBoard;
import com.example.demo.guestboard.dto.InfiniteScroll;


@Service
public class GuestBoardService {
  @Autowired
  private GuestBoardDao guestBoardDao;

  public GuestBoard create(GuestBoard guestBoard) {
    guestBoardDao.insert(guestBoard);
    return guestBoard;
  }


  public List<GuestBoard> getGuestBoardList(int offset, int limit) {
    InfiniteScroll scroll = new InfiniteScroll();
    scroll.setOffset(offset);
    scroll.setLimit(limit);
    return guestBoardDao.select(scroll);
  }
} 
