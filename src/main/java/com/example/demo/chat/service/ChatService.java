package com.example.demo.chat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.chat.dao.ChatMemberDao;
import com.example.demo.chat.dao.ChatMessageDao;
import com.example.demo.chat.dao.ChatRoomDao;
import com.example.demo.chat.dto.ChatMember;
import com.example.demo.chat.dto.ChatMessage;
import com.example.demo.chat.dto.ChatMessageResponse;
import com.example.demo.chat.dto.ChatRoom;
import com.example.demo.chat.dto.ChatRoomResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomDao chatRoomDao;
    private final ChatMemberDao chatMemberDao;
    private final ChatMessageDao chatMessageDao;

    // 채팅방 생성
    public ChatRoom createRoom(Long loninMid, Long mid) {

      ChatRoom dbRoom = chatRoomDao.selectByMembers(loninMid, mid);
    if (dbRoom != null) {
        return dbRoom;
    }

    ChatRoom room = new ChatRoom();
    room.setIsGroup("N");
    room.setStatus("ACTIVE");
    chatRoomDao.insert(room);

    ChatMember cm1 = new ChatMember();
    cm1.setRoomId(room.getId());
    cm1.setMemberId(loninMid);
    chatMemberDao.insert(cm1);

    ChatMember cm2 = new ChatMember();
    cm2.setRoomId(room.getId());
    cm2.setMemberId(mid);
    chatMemberDao.insert(cm2);

    return chatRoomDao.selectById(room.getId());
}

    // 내 방 목록(최근 순)
    public List<ChatRoomResponse> getRoomList(Long loginMid) {
        return chatRoomDao.selectByMid(loginMid);
    }

    // 메시지 저장 + 방 최근 시간 갱신
    @Transactional
    public ChatMessage saveMessage(ChatMessage message) {
        chatMessageDao.insert(message);
        chatRoomDao.touchUpdatedAt(message.getRoomId());
        return message;
    }

    // 메시지 하나
    public ChatMessageResponse getMessage(Long id) {
    return chatMessageDao.selectById(id);
}

    // 방 히스토리
    public List<ChatMessageResponse> getMessages(Long roomId) {
       return chatMessageDao.selectByRoomId(roomId);
    }
}