package org.jhcho.blog.chat.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jhcho.blog.chat.entity.ChatMessage;
import org.jhcho.blog.chat.entity.ChatRoom;
import org.jhcho.blog.chat.service.ChatRoomAction;
import org.jhcho.blog.chat.service.ChatService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

    private final ObjectMapper mapper;

    private final ChatService service;

//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        log.info(session.toString());
//    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);

//        TextMessage textMessage = new TextMessage("Welcome Chatting Server");
//        session.sendMessage(textMessage);
        ChatMessage chatMessage = mapper.readValue(payload, ChatMessage.class);
        log.info("session {}", chatMessage.toString());

        ChatRoomAction chatRoomAction = service.findByRoom(chatMessage.getRoomId());

        log.info("roomId {}", chatMessage.getRoomId().toString());
        log.info("chat room {}", service.findByRoom(chatMessage.getRoomId()));
        chatRoomAction.handleAction(session, chatMessage, service);
    }
}