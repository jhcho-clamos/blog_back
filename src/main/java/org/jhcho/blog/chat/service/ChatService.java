package org.jhcho.blog.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jhcho.blog.chat.converter.ChatConverter;
import org.jhcho.blog.chat.entity.ChatRoom;
import org.jhcho.blog.chat.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
    private final ObjectMapper objectMapper;
    private final ChatRoomRepository chatRoomRepository;
    private Map<String, ChatRoomAction> chatRoomList;

    @Transactional
    @PostConstruct
    public void init() {
        chatRoomList = new LinkedHashMap<>();
        List<ChatRoom> roomList = chatRoomRepository.findAll();
        List<ChatRoomAction> roomActionList = roomList.stream().map(ChatConverter::chatRoomToChatRoomAction).collect(Collectors.toList());
        roomActionList.forEach((item) -> chatRoomList.put(item.getRoomId(), item));
    }


    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    // 특정 방에 대한 정보
    public ChatRoomAction findByRoom(String roomId) {
        log.info(String.valueOf(chatRoomList));
        return chatRoomList.get(roomId);
    }

    // 모든 방 검색
    public List<ChatRoom> findAllRooms() {
        return chatRoomRepository.findAll();
    }


    // d
    public Map<String, ChatRoomAction> returnall() {
        return chatRoomList;
    }

    public ChatRoom createRoom(String roomName) {
        String roomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.builder().roomId(roomId).roomName(roomName).build();
        if (chatRoom != null) {
            ChatRoomAction chatRoomAction = ChatRoomAction.builder().roomId(chatRoom.getRoomId()).roomName(chatRoom.getRoomName()).createDate(chatRoom.getCreateDate()).build();
            chatRoomRepository.save(chatRoom);
            chatRoomList.put(chatRoomAction.getRoomId(), chatRoomAction);
            log.info("room create success!", chatRoomAction);
        }
        return chatRoom;
    }
}
