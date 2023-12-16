package org.jhcho.blog.chat.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

    @Column(name = "password", nullable = false)
    private String password;

    @CreatedDate
    @Column(name = "createdate")
    private LocalDateTime createDate;

    @Column(name = "pwstatus")
    private boolean pwStatus = false;

    @Builder
    public ChatRoom(Long roomId, String roomName, String password, boolean pwStatus) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.password = password;
        this.pwStatus = pwStatus;
    }
}
