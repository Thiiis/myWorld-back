package com.example.demo.jukebox.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.jukebox.dto.Jukebox;

@Mapper
public interface JukeboxDao {
  public Long insert(Jukebox jukebox);
  public Jukebox selectByJid(Long jid);
  public List<Jukebox> selectListByMid(Long mid);
  public Long countByMid(Long mid);
  public Long update(Jukebox jukebox);
  public Long delete(Long jid);
}
