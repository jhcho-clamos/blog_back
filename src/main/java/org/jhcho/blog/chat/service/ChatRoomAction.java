//package org.jhcho.blog.chat.service;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//import lombok.extern.slf4j.Slf4j;
//import org.jhcho.blog.chat.entity.ChatMessage;
//import org.jhcho.blog.chat.enums.MessageData;
//import org.jhcho.blog.chat.enums.MessageStatus;
//import org.springframework.web.socket.WebSocketSession;
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Getter
//@NoArgsConstructor
//@Setter
//@Slf4j
//@Data
//public class ChatRoomAction {
//    @JsonIgnore
//    private Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
//    private String roomId;
//    private String roomName;
//    private LocalDateTime createDate;
//
//    @Builder
//    public ChatRoomAction(String roomId, String roomName, LocalDateTime createDate) {
//        this.roomId = roomId;
//        this.roomName = roomName;
//        this.createDate = createDate;
//    }
//
//    public void handleAction(WebSocketSession session, ChatMessage message, ChatService service) {
//        if (message.getType().toString().equals(MessageStatus.ENTER.toString())) {
//            message.setMessage(message.getSender() + MessageData.ENTER.getMsgType());
//            log.warn("user message enter.");
//        } else if (message.getType().toString().equals(MessageStatus.TALK.toString())) {
//            log.warn("user message talk.");
//        } else if (message.getType().toString().equals(MessageStatus.EXIT.toString())) {
//            log.warn("user message out session.");
//        } else {
//            log.warn("no find message type.");
//        }
//            sessions.put(message.getSender(), session);
//        sendMessage(message, service, message.getRoomId());
//    }
//
//    public <T> void sendMessage(T message, ChatService service, String roomId) {
//        log.info("log count {}", sessions.size());
//        sessions.values().parallelStream().forEach(session -> service.sendMessage(session, message));
//    }
//
//}
