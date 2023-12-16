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

import java.sql.Timestamp;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
@NoArgsConstructor
@Setter
@Table(name = "chatmessage")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "sender")
    private String sender;

    @LastModifiedDate
    @Column(name = "updatetime")
    private LocalDateTime updateTime;

    @Column(name = "roomid")
    private Long roomId;

    @Column(name = "roomname")
    private String roomName;

    @Column(name = "message")
    private String message;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MessageStatus type;


    @Builder
    public ChatMessage(String sender, String roomName, LocalDateTime updateTime, Long roomId, String message, MessageStatus type) {
        this.sender = sender;
        this.roomName = roomName;
        this.updateTime = updateTime;
        this.roomId = roomId;
        this.message = message;
        this.type = type;
    }
}
