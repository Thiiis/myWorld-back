package com.example.demo.diary.service;

import com.example.demo.diary.dao.DiaryDao;
import com.example.demo.diary.dto.Diary;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DiaryService {
    private final DiaryDao diaryDao;

    public void create(Diary diary) {
        diaryDao.insert(diary);
    }
}
