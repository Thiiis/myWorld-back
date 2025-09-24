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

  public JukeboxCreateResponse create(JukeboxCreateRequest request) {
    Long count = jukeboxDao.countByMid(request.getMid());
    if (count >= 10) {
      throw new IllegalStateException("유저당 10개까지만 생성 가능");
    }
    Jukebox jukebox = new Jukebox(request.getMid(), request.getTitle(), request.getContent());
    jukeboxDao.insert(jukebox);
    Jukebox dbJukebox = jukeboxDao.selectByJid(jukebox.getJid());
    return new JukeboxCreateResponse(dbJukebox.getJid(), dbJukebox.getCreatedAt(), dbJukebox.getUpdatedAt());
  }

  public Long update(JukeboxUpdateRequest request) {
    Jukebox jukebox = new Jukebox(request.getJid(), request.getTitle(), request.getContent(), true);
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

  public Long delete(Long jid) {
    return jukeboxDao.delete(jid);
  }

}
