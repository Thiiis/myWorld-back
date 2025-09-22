package com.example.demo.guestboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.guestboard.dto.GuestBoard;
import com.example.demo.guestboard.dto.InfiniteScroll;


// CREATE TABLE "GuestBoard" (
//     "guestboardId" NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
//     "guestId" NUMBER NOT NULL,
//     "profileId"  NUMBER NOT NULL,
//     "content" VARCHAR2(200 CHAR) NOT NULL,
//     "visibility" VARCHAR2(10) DEFAULT 'PUBLIC' NOT NULL CHECK ("visibility" IN ('PUBLIC', 'FRIENDS', 'PRIVATE')),
//     "createdAt" TIMESTAMP WITH TIME ZONE DEFAULT SYSTIMESTAMP NOT NULL,
//     "updatedAt" TIMESTAMP WITH TIME ZONE DEFAULT SYSTIMESTAMP NOT NULL,
//     CONSTRAINT "fk_guestboard_guest" FOREIGN KEY ("guestId") REFERENCES "User"("userId") ON DELETE CASCADE,
//     CONSTRAINT "fk_guestboard_profile" FOREIGN KEY ("profileId") REFERENCES "Profile"("profileId") ON DELETE CASCADE
// );
// COMMENT ON TABLE "GuestBoard" IS '방명록';
// COMMENT ON COLUMN "GuestBoard"."profileId" IS '방명록 주인 프로필 ID (FK)';
// COMMENT ON COLUMN "GuestBoard"."guestId" IS '방명록 작성자 ID (FK)';

@Mapper
public interface GuestBoardDao {
  public int insert(GuestBoard guestBoard);
  public List<GuestBoard> select(InfiniteScroll infiniteScroll);
  public int update(GuestBoard guestBoard);
  public int delete(int guestBoardId);
}
