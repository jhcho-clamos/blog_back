package org.jhcho.blog.chat.controller;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.jhcho.blog.api_response.ApiResponse;
import org.jhcho.blog.chat.entity.ChatRoom;
import org.jhcho.blog.chat.service.ChatRoomAction;
import org.jhcho.blog.chat.service.ChatService;
import org.jhcho.blog.entity.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping
    public ResponseEntity<Message> findAllRooms() {
        List<ChatRoom> chatRooms = chatService.findAllRooms();
        if (chatRooms != null) {
            return new ApiResponse<>(200, "ok", chatRooms).toResponseEntity();
        } else {
            return new ApiResponse<>(400, "존재하는 방이 없습니다.", null).toResponseEntity();
        }
    }

    @PostMapping
    public ResponseEntity<Message> createRoom(@RequestParam("roomName") String roomName) {
        ChatRoom chatRooms = chatService.createRoom(roomName);
        if (chatRooms != null) {
            return new ApiResponse<>(200, "ok", chatRooms).toResponseEntity();
        } else {
            return new ApiResponse<>(400, "방 생성에 실패 하였습니다.", null).toResponseEntity();
        }
    }

    @PostMapping("/findroom")
    public ResponseEntity<Message> findRoom(@RequestParam("roomId") String roomId) {
        ChatRoomAction chatRooms = chatService.findByRoom(roomId);

        if (chatRooms != null) {
            return new ApiResponse<>(200, "ok", chatRooms).toResponseEntity();
        } else {
            return new ApiResponse<>(400, "해당 방을 찾을 수 없습니다.", null).toResponseEntity();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Message> all() {
        return new ApiResponse<>(200, "ok", chatService.returnall()).toResponseEntity();
    }

}
