package org.jhcho.blog.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ChatRoomDTO {
    private Long roomId;
    private String roomName;
    private boolean pwStatus;
    private String makeUser;
    private LocalDateTime createDate;

    @Builder
    public ChatRoomDTO(Long roomId, String roomName, boolean pwStatus, String makeUser, LocalDateTime createDate) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.pwStatus = pwStatus;
        this.makeUser = makeUser;
        this.createDate = createDate;
    }
}
