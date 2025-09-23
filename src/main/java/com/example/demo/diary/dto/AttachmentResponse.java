package com.example.demo.diary.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AttachmentResponse {

  private Long aid;
  private String aname;
  private String atype;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
  
  // 방법 1
  public AttachmentResponse(Attachment attachment) {
    this.aid = attachment.getAid();
    this.aname = attachment.getAname();
    this.atype = attachment.getAtype();
    this.createdAt = attachment.getCreatedAt();
    this.updatedAt = attachment.getUpdatedAt();
  }
  
  // 방법 2
  // public AttachmentResponse(Long aid, String aname, String atype, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
  //   this.aid = aid;
  //   this.aname = aname;
  //   this.atype = atype;
  //   this.createdAt = createdAt;
  //   this.updatedAt = updatedAt;
  // }

}
