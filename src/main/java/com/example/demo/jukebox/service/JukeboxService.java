package com.example.demo.jukebox.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.jukebox.dao.JukeboxDao;
import com.example.demo.jukebox.dto.Jukebox;
import com.example.demo.jukebox.dto.JukeboxCreateRequest;
import com.example.demo.jukebox.dto.JukeboxCreateResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JukeboxService {
  private final JukeboxDao jukeboxDao;

  public JukeboxCreateResponse create(JukeboxCreateRequest request) {
    Jukebox jukebox = new Jukebox(request);
    jukeboxDao.insert(jukebox);
    return new JukeboxCreateResponse(jukebox);
  }

  public List<Jukebox> getJukeboxList() {
    return jukeboxDao.selectJukeboxList();
  }

  public Jukebox getJukebox(Long jid) {
    Jukebox jukebox = jukeboxDao.selectByJid(jid);
    return jukebox;
  }

  public Long update(Jukebox jukebox) {
    return jukeboxDao.update(jukebox);
  }

  public Long delete(Long jid) {
    Long rows = jukeboxDao.delete(jid);
    return rows;
  }

}
