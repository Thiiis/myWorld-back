package com.example.demo.profile.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProfileFKUpdateRequest {
    private Long pid;
    private Long mid;
    private Long jid;
    private Long tid;

    public ProfileFKUpdateRequest(Long pid, Long mid, Long jid, Long tid){
        this.pid = pid;
        this.mid = mid;
        this.jid = jid;
        this.tid = tid;
    }
}
