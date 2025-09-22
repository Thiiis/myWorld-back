package com.example.demo.diary.dto;

import java.time.OffsetDateTime;

import lombok.Getter;

@Getter
public class AttachmentResponse {
  private Long aid;
  private String aname;
  private String atype;
  private OffsetDateTime createdAt;

  public AttachmentResponse() {}
  public AttachmentResponse(Long aid, String aname, String atype, OffsetDateTime createdAt) {
    this.aid = aid;
    this.aname = aname;
    this.atype = atype;
    this.createdAt = createdAt;
  }

  // public void setAid(Long aid) { this.aid = aid; }
  // public void setAname(String aname) { this.aname = aname; }
  // public void setAtype(String atype) { this.atype = atype; }
  // public void setAdata(OffsetDateTime createdAt) { this.createdAt = createdAt; }
  
}
