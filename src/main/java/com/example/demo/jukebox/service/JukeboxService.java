package com.example.demo.jukebox.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

  public JukeboxCreateResponse create(JukeboxCreateRequest dto) {
    int count = jukeboxDao.countByMid(dto.getMid());
    if (count >= 10) {
      throw new IllegalStateException("유저당 10개까지만 생성 가능");
    }
    Jukebox jukebox = new Jukebox(dto.getMid(), dto.getTitle(), dto.getContent());
    jukeboxDao.insert(jukebox);
    Jukebox dbJukebox = jukeboxDao.selectByJid(jukebox.getJid());
    return new JukeboxCreateResponse(dbJukebox.getJid(), dbJukebox.getCreatedAt(), dbJukebox.getUpdatedAt());
  }

  public int update(JukeboxUpdateRequest dto) {
    Jukebox jukebox = new Jukebox(dto.getJid(), dto.getTitle(), dto.getContent(), true);
    return jukeboxDao.update(jukebox);
  }

  public List<JukeboxListResponse> getJukeboxList(Long mid) {
    List<Jukebox> list = jukeboxDao.selectListByMid(mid);
    return list.stream().map(j -> new JukeboxListResponse(j.getJid(), j.getTitle())).toList();
  }

  public JukeboxDetailResponse getJukeboxDetail(Long jid) {
    Jukebox jukebox = jukeboxDao.selectByJid(jid);
    return new JukeboxDetailResponse(jukebox.getJid(), jukebox.getTitle(), jukebox.getContent(), jukebox.getCreatedAt(), jukebox.getUpdatedAt());
  }

  public void delete(Long jid) {
    jukeboxDao.delete(jid);
  }

}
