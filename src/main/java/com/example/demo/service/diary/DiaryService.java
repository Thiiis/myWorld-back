package com.example.demo.service.diary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DiaryDao;
import com.example.demo.dto.diary.Diary;
import com.example.demo.dto.diary.Visibility;

@Service
public class DiaryService {

  @Autowired
  private DiaryDao diaryDao;

   public void createDiary(Diary diary) {
        if(diary.getVisibility() == null) {
            diary.setVisibility(Visibility.PUBLIC);
        }
        diaryDao.insertDiary(diary);
    }
}
