package com.example.demo.diary.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiarysRequest {

  private Long did;               //update
  private Long hostid;            //create
  private String title;           //create,read,update
  private String content;         //create,read,update
  private String emo;             //create,read,update -HAPPY, CALM, EXCITED, SAD
  private String weather;         //create,read,update -SUNNY, CLOUDY, RAINY, SNOWY
  private List<Long> deleteAids;  //delete -다중 삭제용
}