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
    Track track = new Track(dto.getJid(), dto.getSid());
    trackDao.insert(track);
    Track dbTrack = trackDao.selectByTrid(track.getTrid());
    return new TrackCreateResponse(dbTrack.getTrid(), dbTrack.getJid(), dbTrack.getSid());
  }

  public List<TrackListResponse> getTrackListByJid(Long jid) {
    List<TrackListResponse> tracks = trackDao.selectSongsByJid(jid);
    return tracks.stream().map(s -> new TrackListResponse(s.getTrid(), s.getSid(), s.getTitle(), s.getArtist(), s.getDuration(), s.getVideoId())).toList();
  }

  public int delete(Long trid) {
    return trackDao.delete(trid);
  }

}
