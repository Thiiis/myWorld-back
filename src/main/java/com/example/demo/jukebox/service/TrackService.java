package com.example.demo.jukebox.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.jukebox.dao.TrackDao;
import com.example.demo.jukebox.dto.Song;
import com.example.demo.jukebox.dto.Track;
import com.example.demo.jukebox.dto.TrackCreateRequest;
import com.example.demo.jukebox.dto.TrackCreateResponse;
import com.example.demo.jukebox.dto.TrackListResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrackService {
  private final TrackDao trackDao;

  public TrackCreateResponse create(TrackCreateRequest dto) {
    Track track = new Track(dto.getJid(), dto.getSid(), dto.getTrackOrder());
    trackDao.insert(track);
    Track dbTrack = trackDao.selectByTrid(track.getTrid());
    return new TrackCreateResponse(dbTrack.getTrid(), dbTrack.getJid(), dbTrack.getSid(), dbTrack.getTrackOrder());
  }

  public List<TrackListResponse> getTrackListByJid(Long jid) {
    List<Song> songs = trackDao.selectSongsByJid(jid);
    return songs.stream().map(s -> new TrackListResponse(s.getTitle(), s.getArtist())).toList();
  }

  public int delete(Long trid) {
    return trackDao.delete(trid);
  }
}
