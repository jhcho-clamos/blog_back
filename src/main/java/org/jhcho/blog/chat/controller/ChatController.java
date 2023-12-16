package org.jhcho.blog.chat.controller;

import lombok.RequiredArgsConstructor;
import org.jhcho.blog.api_response.ApiResponse;
import org.jhcho.blog.chat.dto.ChatRoomDTO;
import org.jhcho.blog.chat.entity.ChatMessage;
import org.jhcho.blog.chat.entity.ChatRoom;
import org.jhcho.blog.chat.service.ChatService;
import org.jhcho.blog.entity.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/api/chat")
    public ResponseEntity<Message> findAllRooms() {
        List<ChatRoomDTO> chatRooms = chatService.findAllRooms();
        if (chatRooms != null) {
            return new ApiResponse<>(200, "ok", chatRooms).toResponseEntity();
        } else {
            return new ApiResponse<>(400, "존재하는 방이 없습니다.", null).toResponseEntity();
        }
    }

    @PostMapping("/api/chat/access")
    public ResponseEntity<Message> roomPwAccess(@RequestBody ChatRoom chatRoom) {
        boolean accessPw = chatService.roomPwAccess(chatRoom);
        if (accessPw) {
            return new ApiResponse<>(200, "ok", "access").toResponseEntity();
        } else {
            return new ApiResponse<>(200, "fail", "fail").toResponseEntity();
        }
    }

    @PostMapping("/api/chat")
    public ResponseEntity<Message> createRoom(@RequestBody ChatRoom chatRoom) {
        ChatRoom chatRooms = chatService.createRoom(chatRoom);
        if (chatRooms != null) {
            return new ApiResponse<>(200, "ok", chatRooms).toResponseEntity();
        } else {
            return new ApiResponse<>(400, "방 생성에 실패 하였습니다.", null).toResponseEntity();
        }
    }

//    @MessageMapping("/chat/{roomId}")
//    @SendTo("/sub/chat/{roomId}")
//    public ChatMessage message(ChatMessage message, @DestinationVariable("roomId") Long roomId) {
//        message.setRoomId(roomId);
//        ChatMessage msg = chatService.sendMessage(message);
//        return msg;
//    }

    @GetMapping("/chat/redis/{id}")
    public List<Object> all(@PathVariable("id") String id) {
        return chatService.all(id);
    }

    @MessageMapping("/chat/{roomId}")
    public void message(ChatMessage message, @DestinationVariable("roomId") Long roomId) {
        message.setRoomId(roomId);
        chatService.sendMessageRedis(message);
    }
}
