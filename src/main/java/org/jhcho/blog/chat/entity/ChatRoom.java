package org.jhcho.blog.chat.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
@NoArgsConstructor
@Table(name = "chatroom")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomid")
    private Long roomId;

    @Column(name = "roomname")
    private String roomName;

    @CreatedDate
    @Column(name = "createdate")
    private LocalDateTime createDate;

    @Builder
    public ChatRoom(Long roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }
}
