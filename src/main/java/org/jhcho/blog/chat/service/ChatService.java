package org.jhcho.blog.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jhcho.blog.chat.entity.ChatMessage;
import org.jhcho.blog.chat.entity.ChatRoom;
import org.jhcho.blog.chat.enums.MessageData;
import org.jhcho.blog.chat.enums.MessageStatus;
import org.jhcho.blog.chat.repository.ChatRoomRepository;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final ObjectMapper objectMapper;

    // 모든 방 검색
    public List<ChatRoom> findAllRooms() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom createRoom(String roomName) {
        ChatRoom chatRoom = ChatRoom.builder().roomName(roomName).build();
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }

    // 메시지 보내기
    public ChatMessage sendMessage(ChatMessage message) {
        if (MessageStatus.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + MessageData.ENTER.getMsgType());
        }
        return message;
    }
}
