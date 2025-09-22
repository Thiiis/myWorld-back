package com.example.demo.guestboard.dto;

import java.util.Date;

import lombok.Data;

@Data
public class GuestBoard {
  private int gbid;
  private int gid;
  private int pid;
  private String content;
  private String view_scope;
  private Date createdAt;
  private Date updatedAt;
}
