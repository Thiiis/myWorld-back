package com.example.demo.jukebox.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.jukebox.dto.Song;
import com.example.demo.jukebox.dto.Track;

@Mapper
public interface TrackDao {
  public Long insert(Track track);
  public Track selectByTrid(Long Trid);
  public List<Song> selectSongsByJid(Long jid);
  public Long delete(Long trid);
}
