package com.example.demo.jukebox.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.jukebox.dao.JukeboxDao;
import com.example.demo.jukebox.dto.Jukebox;
import com.example.demo.jukebox.dto.JukeboxCreateRequest;
import com.example.demo.jukebox.dto.JukeboxCreateResponse;
import com.example.demo.jukebox.dto.JukeboxDetailResponse;
import com.example.demo.jukebox.dto.JukeboxListResponse;
import com.example.demo.jukebox.dto.JukeboxUpdateRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JukeboxService {

  private final JukeboxDao jukeboxDao;

  public JukeboxCreateResponse create(Long mid, JukeboxCreateRequest dto) {
    int count = jukeboxDao.countByMid(mid);
    if (count >= 10) {
      throw new IllegalStateException("유저당 10개까지만 생성 가능");
    }
    Jukebox jukebox = new Jukebox(mid, dto.getTitle(), dto.getContent());
    jukeboxDao.insert(jukebox);
    Jukebox dbJukebox = jukeboxDao.selectByJid(jukebox.getJid());
    return new JukeboxCreateResponse(dbJukebox.getJid(), dbJukebox.getTitle(), dbJukebox.getContent(), dbJukebox.getCreatedAt());
  }

  public int update(Long mid, JukeboxUpdateRequest dto) {
    if(!jukeboxDao.selectByJid(dto.getJid()).getMid().equals(mid)) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인 글만 수정할 수 있습니다.");
    }
    Jukebox newJukebox = new Jukebox(dto.getJid(), dto.getTitle(), dto.getContent(), true);
    return jukeboxDao.update(newJukebox);
  }

  public List<JukeboxListResponse> getJukeboxList(Long mid) {
    List<Jukebox> list = jukeboxDao.selectListByMid(mid);
    return list.stream().map(j -> new JukeboxListResponse(j.getJid(), j.getTitle())).toList();
  }

  public JukeboxDetailResponse getJukeboxDetail(Long jid) {
    Jukebox jukebox = jukeboxDao.selectByJid(jid);
    return new JukeboxDetailResponse(jukebox.getJid(), jukebox.getTitle(), jukebox.getContent(), jukebox.getCreatedAt(), jukebox.getUpdatedAt());
  }

  public void delete(Long mid, Long jid) {
    if(!jukeboxDao.selectByJid(jid).getMid().equals(mid)) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인 글만 삭제할 수 있습니다.");
    }
    jukeboxDao.delete(jid);
  }

}
