package com.example.demo.jukebox.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.jukebox.dto.JukeboxLikes;

@Mapper
public interface JukeboxLikesDao {
  public int insert(JukeboxLikes jukeboxLikes);
  public JukeboxLikes selectByJidAndMid(@Param("jid") Long jid, @Param("mid") Long mid);
  public int delete(@Param("jid") Long jid, @Param("mid") Long mid);
}
