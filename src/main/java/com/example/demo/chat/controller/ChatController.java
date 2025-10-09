package com.example.demo.chat.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.chat.dto.ChatMessage;
import com.example.demo.chat.dto.ChatMessageResponse;
import com.example.demo.chat.dto.ChatRoom;
import com.example.demo.chat.dto.ChatRoomResponse;
import com.example.demo.chat.service.ChatService;
import com.example.demo.interceptor.Login;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    // 채팅방 생성
    @Login
    @PostMapping("/rooms")
    public ChatRoom createRoom(@RequestParam("mid") Long mid, HttpServletRequest request) {
        Long loginMid = (Long) request.getAttribute("loginMid");
        return chatService.createRoom(loginMid, mid);
    }

    // 내 채팅방 목록
    @Login
    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoomResponse>> getRoomList(HttpServletRequest request) {
        Long loginMid = (Long) request.getAttribute("loginMid");
        List<ChatRoomResponse> rooms = chatService.getRoomList(loginMid);
        return ResponseEntity.ok(rooms);
    }

    // 채팅방 조회
    @Login
    @GetMapping("/rooms/{roomId}/messages")
    public ResponseEntity<List<ChatMessageResponse>> getMessages(@PathVariable("roomId") Long roomId) {
        List<ChatMessageResponse> messages = chatService.getMessages(roomId);
        return ResponseEntity.ok(messages);
    }

    // 실시간 메시지 송수신
    @MessageMapping("/chat.send.{roomId}")
    public void sendMessage(@DestinationVariable("roomId") Long roomId,
                            ChatMessage message,
                            SimpMessageHeaderAccessor accessor) {
        Long senderId = (Long) accessor.getSessionAttributes().get("loginMid");

        message.setSenderId(senderId);
        message.setRoomId(roomId);

        ChatMessage dbMessage = chatService.saveMessage(message);
        ChatMessageResponse dto = chatService.getMessage(dbMessage.getId());

        messagingTemplate.convertAndSend("/topic/rooms/" + roomId, dto);
    }
}
