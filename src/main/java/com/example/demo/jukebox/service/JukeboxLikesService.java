package com.example.demo.jukebox.service;

import org.springframework.stereotype.Service;

import com.example.demo.jukebox.dao.JukeboxLikesDao;
import com.example.demo.jukebox.dto.JukeboxLikes;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JukeboxLikesService {
  private final JukeboxLikesDao jukeboxLikesDao;

  public boolean toggleLike(Long jid, Long mid) {
    JukeboxLikes isLike = jukeboxLikesDao.selectByJidAndMid(jid, mid);

    if(isLike != null) {
      jukeboxLikesDao.delete(jid, mid);
      return false;
    } else {
      JukeboxLikes newLike = new JukeboxLikes(jid, mid);
      jukeboxLikesDao.insert(newLike);
      return true;
    }
  }
}
