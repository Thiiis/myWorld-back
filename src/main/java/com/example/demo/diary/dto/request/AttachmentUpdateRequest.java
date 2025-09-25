package com.example.demo.diary.dto.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AttachmentUpdateRequest {
  private Long did;
  private List<MultipartFile> attach;
}
