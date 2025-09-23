package com.example.demo.diary.dto;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Attachment {

  private Long aid;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
  private Long did;
  private String aname;
  private String atype;
  private byte[] adata; //파일 데이터(BLOB) // 다운로드 금지 정책으로 응답에서는 제외하고 업로드 시에만 사용

  // 방법 1. 객체로 받는 방식
//   public Attachment(AttachmentCreateRequest request) {
//     this.aname = request.getAname();
//     this.atype = request.getAtype();
//     this.adata = request.getAdata();
//   }

//   // 방법 2. 생성자 오버로딩 방식
//   public Attachment(Long aid, Long did, String aname, String atype, byte[] adata, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
//     this.aid = aid;
//     this.did = did;
//     this.aname = aname;
//     this.atype = atype;
//     this.adata = adata;
//     this.createdAt = createdAt;
//     this.updatedAt = updatedAt;
//   }

    // 정적 팩토리 메서드 - 생성 시점
    //public static

  // --- 도메인 행위 메서드 --- entity에서는 update필드명메소드 스타일로 두는것이 더 좋다.(방법2.메소드 개별로 생성)
    public void updateAid(Long aid) {
        if (aid == null) throw new IllegalArgumentException("값이 없을 수 없음");
        this.aid = aid;
        this.updatedAt = OffsetDateTime.now();
    }

    public void updateDid(Long did) {
        if (did == null) throw new IllegalArgumentException("값이 없을 수 없음");
        this.did = did;
        this.updatedAt = OffsetDateTime.now();
    }

    public void updateAname(String aname) {
        if (aname == null || aname.isBlank()) throw new IllegalArgumentException("파일명은 비어 있을 수 없음");
        this.aname = aname;
        this.updatedAt = OffsetDateTime.now();
    }

    public void updateAtype(String atype) {
        if (atype == null || atype.isBlank()) throw new IllegalArgumentException("파일 타입은 비어 있을 수 없음");
        this.atype = atype;
        this.updatedAt = OffsetDateTime.now();
    }

    public void updateAdata(byte[] adata) {
        if (adata == null) throw new IllegalArgumentException("파일 데이터는 비어 있을 수 없음");
        this.adata = adata;
        this.updatedAt = OffsetDateTime.now();
    }

    public void updateCreatedAt(OffsetDateTime createdAt) {
        if (createdAt == null) throw new IllegalArgumentException("생성일은 null일 수 없음");
        this.createdAt = createdAt;
    }

    public void updateUpdatedAt(OffsetDateTime updatedAt) {
        if (updatedAt == null) throw new IllegalArgumentException("수정일은 null일 수 없음");
        this.updatedAt = updatedAt;
    }
}