package org.jhcho.blog.chat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.jhcho.blog.chat.enums.MessageStatus;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
@Setter
@RedisHash(value = "chatmessage", timeToLive = 1209600)
public class ChatMessage implements Serializable {

    @Id
    @JsonIgnore
    private String id = UUID.randomUUID().toString();

    private String sender;


    private String updateTime = LocalDateTime.now().toString();

    private Long roomId;

    private String roomName;

    private String message;

    private MessageStatus type;


    @Builder
    public ChatMessage(String sender, String roomName, String updateTime, Long roomId, String message, MessageStatus type) {
        this.sender = sender;
        this.roomName = roomName;
        this.updateTime = updateTime;
        this.roomId = roomId;
        this.message = message;
        this.type = type;
    }
}
