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
import com.example.demo.jukebox.dto.SongSearchResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SongService {
  private final SongDao songDao;

  // @Value("${youtube.api.key}")
  // private String apiKey;

  // // 음악 검색하기
  // // 10개만 가져오기
  // public List<SongSearchResponse> searchSongs(String search) {
  //   String url = "https://www.googleapis.com/youtube/v3/search"
  //     + "?part=snippet" // 제목, 채널 이름
  //     + "&type=video" // 동영상만 검색
  //     + "&videoCategoryId=10" // 음악 카테고리
  //     + "&maxResults=10"
  //     + "&q=" + search
  //     + "&key=" + apiKey;

  //   // HTTP 통신 처리. 요청을 보내기 위해 RestTemplate 객체 생성
  //   RestTemplate restTemplate = new RestTemplate();
  //   // HTTP GET 요청
  //   String response = restTemplate.getForObject(url, String.class);

  //   JsonNode root = null;
  //   try {
  //     root = new ObjectMapper().readTree(response);
  //   } catch (JsonMappingException e) {
  //     // 매핑 과정에서 구조 불일치가 생겼을 때 오류 처리
  //     e.printStackTrace();
  //   } catch (JsonProcessingException e) {
  //     // json 파싱 전반에서 발생하는 일반적인 오류
  //     e.printStackTrace();
  //   }
  //   List<SongSearchResponse> results = new ArrayList<>();

  //   // 응답 json의 items 배열 순회
  //   for (JsonNode item : root.get("items")) {
            // id 노드 꺼내기
  //       JsonNode idNode = item.get("id");
        
  //       // null 체크. videoId가 null이면 건너뛰기
  //       if (idNode == null || !idNode.has("videoId")) {
  //           continue;
  //       }

  //       asText() -> json 형태라서 string으로 받아야함
  //       String videoId = idNode.get("videoId").asText();
  //       String title = item.get("snippet").get("title").asText();
  //       String artist = item.get("snippet").get("channelTitle").asText();
  //       String thumbnail = item.get("snippet").get("thumbnails").get("default").get("url").asText();

  //       results.add(new SongSearchResponse(videoId, title, artist, thumbnail));
  //   }

  //   return results;
  // }


  // 음악 추가하기
  // duration PT 앞에 빼야됨
  // PT3M30S
 // Long duration = parseDuration인 함수 만들어서 pt 없애고 시간만  long으로 만들어서 반환하기. item안에 contentDetails 안에 duration 있는데 getJSONObject?? node로 다시 만들기?

  // url에 videoId 추가해서 그 저보 가져와서 song에 저장? 
  // Song song 으로 객체 만들어서 insert하기


  // 음악 삭제하기
  public int delete(Long sid) {
    return songDao.delete(sid);
  }
}
