package org.jhcho.blog.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jhcho.blog.chat.entity.ChatMessage;
import org.jhcho.blog.chat.enums.MessageData;
import org.jhcho.blog.chat.enums.MessageStatus;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisSubscriber implements MessageListener {
    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String publishMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());

            ChatMessage roomMessage = objectMapper.readValue(publishMessage, ChatMessage.class);
            log.warn("redis {}", message);
            if (roomMessage.getType().equals(MessageStatus.ENTER)) {
                roomMessage.setMessage(roomMessage.getSender() + MessageData.ENTER.getMsgType());
            }
            messagingTemplate.convertAndSend("/sub/chat/" + roomMessage.getRoomId(), roomMessage);

        } catch (Exception e) {
            log.warn("message error {}", message);
        }
    }
}