package org.jhcho.blog.chat.converter;

import org.jhcho.blog.chat.dto.ChatRoomDTO;
import org.jhcho.blog.chat.entity.ChatRoom;

public class ChatConverter {
    public static ChatRoomDTO chatRoomTochatDTO(ChatRoom chatRoom) {
        ChatRoomDTO chatRoomDTO = ChatRoomDTO.builder().roomName(chatRoom.getRoomName())
                .roomId(chatRoom.getRoomId()).pwStatus(chatRoom.isPwStatus()).createDate(chatRoom.getCreateDate()).build();
        return chatRoomDTO;
    }
}
