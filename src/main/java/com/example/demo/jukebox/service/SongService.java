package com.example.demo.jukebox.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.jukebox.dao.SongDao;
import com.example.demo.jukebox.dto.Song;
import com.example.demo.jukebox.dto.SongCreateRequest;
import com.example.demo.jukebox.dto.SongCreateResponse;
import com.example.demo.jukebox.dto.SongMyResponse;
import com.example.demo.jukebox.dto.SongSearchResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SongService {
  private final SongDao songDao;

  @Value("${youtube.api.key}")
  private String apiKey;

  // 음악 검색하기
  // 관련 높은 10개만 가져오기
  public List<SongSearchResponse> searchSongs(Long mid, String search) {
    String url = "https://www.googleapis.com/youtube/v3/search"
        + "?part=snippet" // 제목, 채널 이름
        + "&type=video" // 동영상만 검색
        + "&videoCategoryId=10" // 음악 카테고리
        + "&maxResults=10"
        + "&q=" + search
        + "&key=" + apiKey;

    // HTTP 통신 처리. 요청을 보내기 위해 RestTemplate 객체 생성
    RestTemplate restTemplate = new RestTemplate();
    // HTTP GET 요청. GET 요청에 대한 결과를 객체로 반환. json 문자열 형태
    String response = restTemplate.getForObject(url, String.class);

    JsonNode root = null;
    try {
      // JSON 문자열 → 트리
      root = new ObjectMapper().readTree(response);
    } catch (JsonMappingException e) {
      // 매핑 과정에서 구조 불일치가 생겼을 때 예외 처리
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      // JSON 파싱 전반에서 발생하는 예외 처리
      e.printStackTrace();
    }

    List<SongSearchResponse> results = new ArrayList<>();

    // 응답 JSON의 items 배열 순회
    for (JsonNode item : root.get("items")) {
      // id 노드 꺼내기
      JsonNode idNode = item.get("id");

      // null 체크. videoId가 null이면 건너뛰기
      if (idNode == null || !idNode.has("videoId")) {
        continue;
      }

      String videoId = idNode.get("videoId").asText();
      String title = item.get("snippet").get("title").asText();
      String artist = item.get("snippet").get("channelTitle").asText();
      String thumbnail = item.get("snippet").get("thumbnails").get("default").get("url").asText();

      results.add(new SongSearchResponse(videoId, title, artist, thumbnail));
    }

    return results;
  }

  // 음악 추가하기
  public SongCreateResponse create(Long mid, SongCreateRequest dto) {
    String videoId = dto.getVideoId();

    // YouTube API 호출
    RestTemplate restTemplate = new RestTemplate();
    String url = "https://www.googleapis.com/youtube/v3/videos"
        + "?part=snippet,contentDetails"
        + "&id=" + videoId
        + "&key=" + apiKey;

    String result = restTemplate.getForObject(url, String.class);
    JSONObject json = new JSONObject(result);

    JSONObject item = json.getJSONArray("items").getJSONObject(0);
    String title = item.getJSONObject("snippet").getString("title");
    String artist = item.getJSONObject("snippet").getString("channelTitle");
    String durationStr = item.getJSONObject("contentDetails").getString("duration");

    // ISO 8601 duration → 초 단위 변환 (간단하게 예시)
    Long duration = parseDuration(durationStr);

    // 2. DB 저장
    Song song = new Song(mid, title, artist, videoId, duration);
    songDao.insert(song);

    Song dbSong = songDao.selectBySid(song.getSid());
    return new SongCreateResponse(dbSong.getSid(), dbSong.getTitle(), dbSong.getArtist(), dbSong.getVideoId());
  }

  private Long parseDuration(String duration) {
    int minutes = 0;
    int seconds = 0;

    int number = 0;
    for (int i = 2; i < duration.length(); i++) { // "PT"는 건너뛰고 시작
      char c = duration.charAt(i);

      if (c >= '0' && c <= '9') {
        // 문자 '0' ~ '9'를 숫자로 변환
        number = number * 10 + (c - '0');
      } else if (c == 'M') {
        minutes = number;
        number = 0; // 초기화
      } else if (c == 'S') {
        seconds = number;
        number = 0; // 초기화
      }
    }
    return (long) (minutes * 60 + seconds);
  }

  // 내 음악 전체 조회
  public List<SongMyResponse> mySongs(Long mid) {
    List<Song> songs = songDao.selectMySongsByMid(mid);
    return songs.stream().map(song -> new SongMyResponse(song.getTitle(), song.getArtist(), song.getDuration())).toList();
  }

  // 음악 삭제하기
  public int delete(Long mid, Long sid) {
    return songDao.delete(sid);
  }
}
