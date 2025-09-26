package com.example.demo.jukebox.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.jukebox.dto.JukeboxLikes;

@Mapper
public interface JukeboxLikesDao {
  public int insert(JukeboxLikes jukeboxLikes);
  public JukeboxLikes selectByJidAndMid(Long jid, Long mid);
  public int delete(Long jid, Long mid);
}
