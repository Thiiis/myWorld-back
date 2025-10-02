package com.example.demo.jukebox.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.jukebox.dto.Song;

@Mapper
public interface SongDao {
  public int insert(Song song);
  public Song selectBySid(Long sid);
  public List<Song> selectByJid(Long jid);
  public List<Song> selectMySongsByMid(Long mid);
  public int delete(Long sid);
}
