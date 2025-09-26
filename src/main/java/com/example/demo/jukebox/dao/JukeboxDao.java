package com.example.demo.jukebox.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.jukebox.dto.Jukebox;

@Mapper
public interface JukeboxDao {
  public int insert(Jukebox jukebox);
  public Jukebox selectByJid(Long jid);
  public List<Jukebox> selectListByMid(Long mid);
  public int countByMid(Long mid);
  public int update(Jukebox jukebox);
  public int delete(Long jid);
}
