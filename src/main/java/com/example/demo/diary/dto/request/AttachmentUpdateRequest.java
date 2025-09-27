package com.example.demo.diary.dto.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AttachmentUpdateRequest {

  private Long aid;
  private Long did;
  private String aname;
  private String atype;
  private byte[] adata;
  private List<MultipartFile> attach;

  //생성자 (새 파일 추가용)
   public AttachmentUpdateRequest(Long did, String aname, String atype, byte[] adata) {
    this.did = did;
    this.aname = aname;
    this.atype = atype;
    this.adata = adata;
  }

  // 생성자 (기존 파일 수정용)
  public AttachmentUpdateRequest(Long aid, Long did, String aname, String atype, byte[] adata) {
    this.aid = aid;
    this.did = did;
    this.aname = aname;
    this.atype = atype;
    this.adata = adata;
  }
}
