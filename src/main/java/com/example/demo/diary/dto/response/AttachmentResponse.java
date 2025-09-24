package com.example.demo.diary.dto.response;

import java.time.OffsetDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AttachmentResponse {

  private Long aid;
  private String aname;
  private String atype;
  private Date createdAt;
  private Date updatedAt;
  
  public AttachmentResponse(Long aid, String aname, String atype, Date createdAt, Date updatedAt) {
    this.aid = aid;
    this.aname = aname;
    this.atype = atype;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
