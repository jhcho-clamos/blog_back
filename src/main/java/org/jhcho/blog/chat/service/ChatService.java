package org.jhcho.blog.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jhcho.blog.chat.converter.ChatConverter;
import org.jhcho.blog.chat.dto.ChatRoomDTO;
import org.jhcho.blog.chat.entity.ChatMessage;
import org.jhcho.blog.chat.entity.ChatRoom;
import org.jhcho.blog.chat.enums.MessageData;
import org.jhcho.blog.chat.enums.MessageStatus;
import org.jhcho.blog.chat.repository.ChatRoomRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final PasswordEncoder passwordEncoder;

    // 모든 방 검색
    public List<ChatRoomDTO> findAllRooms() {
        List<ChatRoomDTO> list = chatRoomRepository.findAll().stream()
                .map(ChatConverter::chatRoomTochatDTO).collect(Collectors.toList());
        return list;
    }


    // 방 생성
    public ChatRoom createRoom(String roomName, String password) {
        boolean pwStatus = false;
        String pw = "";
        if (!password.equals("")) {
            pwStatus = true;
            pw = passwordEncoder.encode(password);
        }
        ChatRoom chatRoom = ChatRoom.builder().roomName(roomName).password(pw).pwStatus(pwStatus).build();
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }

    // 방 비밀번호 유효성 검사
    public boolean roomPwAccess(ChatRoom chatRoom) {
        Optional<ChatRoom> ch = chatRoomRepository.findById(chatRoom.getRoomId());
        boolean accessPw = passwordEncoder.matches(chatRoom.getPassword(), ch.get().getPassword());
        return accessPw;
    }


    // 메시지 보내기
    public ChatMessage sendMessage(ChatMessage message) {
        if (MessageStatus.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + MessageData.ENTER.getMsgType());
        }
        return message;
    }

    // redis 메시지 보내기
    public void sendMessageRedis(ChatMessage message) {
        if (MessageStatus.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + MessageData.ENTER.getMsgType());
        }
        redisTemplate.opsForList().rightPush(message.getRoomId().toString(), message);
        redisTemplate.convertAndSend("chatroom", message);
    }

    public List<Object> all(String key) {
        List<Object> list = redisTemplate.opsForList().range(key, 0, -1);
        return list;
    }


}
