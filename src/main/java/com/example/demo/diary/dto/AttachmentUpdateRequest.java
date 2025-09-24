package com.example.demo.diary.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AttachmentUpdateRequest {
  private Long did;
  private String aname;
  private String atype;
  private byte[] adata; //파일 데이터(BLOB) // 다운로드 금지 정책으로 응답에서는 제외하고 업로드 시에만 사용
}
