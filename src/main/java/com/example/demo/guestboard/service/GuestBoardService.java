package com.example.demo.guestboard.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.auth.dao.MemberDao;
import com.example.demo.guestboard.dao.GuestBoardDao;
import com.example.demo.guestboard.dto.GuestBoard;
import com.example.demo.guestboard.dto.GuestBoardCreateRequest;
import com.example.demo.guestboard.dto.GuestBoardCreateResponse;
import com.example.demo.guestboard.dto.GuestBoardListRequest;
import com.example.demo.guestboard.dto.GuestBoardListResponse;
import com.example.demo.guestboard.dto.GuestBoardUpdateRequest;
import com.example.demo.profile.dao.ProfileDao;
import com.example.demo.profile.dto.Profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuestBoardService {

  private final GuestBoardDao guestBoardDao;
  private final ProfileDao profileDao;

  // board create
  public GuestBoardCreateResponse create(Long hostid, Long gid, GuestBoardCreateRequest dto) {

    GuestBoard guestBoard = new GuestBoard(hostid, gid, dto.getContent());
    guestBoardDao.insert(guestBoard);
    GuestBoard dbGuestBoard = guestBoardDao.selectByGbid(guestBoard.getGbid());

    // 작성자 프로필
    Profile writerProfile = profileDao.selectByMid(gid);

    return new GuestBoardCreateResponse(dbGuestBoard.getContent(), writerProfile.getNickname(), dbGuestBoard.getCreatedAt());
  }

  // board list
  public List<GuestBoardListResponse> getGuestBoardList(GuestBoardListRequest dto) {
    List<GuestBoard> list = guestBoardDao.select(dto.getOffset(), dto.getLimit());
    return list.stream().map(g -> new GuestBoardListResponse(g.getGbid(), g.getGid(), g.getContent(), g.getCreatedAt(), g.getUpdatedAt())).toList();
  }

  public GuestBoard getGuestBoard(Long gbid) {
    GuestBoard guestBoard = guestBoardDao.selectByGbid(gbid);
    return guestBoard;
  }

  // board update
  public int update(GuestBoardUpdateRequest dto) {
    GuestBoard guestBoard = new GuestBoard(dto.getGbid(), dto.getContent());
    return guestBoardDao.update(guestBoard);
  }

  public int delete(Long gbid, Long mid) {
    GuestBoard dbGuestboard = guestBoardDao.selectByGbid(gbid);
    // 만약에 작성하는 사람(mid) 와 디비에 있는 작성한 값이 같다면 이게 게스트보드의 pid가 저장되어 있자나. 
    if(!mid.equals(dbGuestboard.getGid())) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인 글만 삭제할 수 있습니다.");
    }
    return guestBoardDao.delete(gbid);
  }
} 
