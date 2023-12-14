package org.jhcho.blog.chat.converter;

import org.jhcho.blog.chat.entity.ChatRoom;
import org.jhcho.blog.chat.service.ChatRoomAction;

public class ChatConverter {
    public static ChatRoomAction chatRoomToChatRoomAction(ChatRoom chatRoom) {
        ChatRoomAction chatRoomAction = new ChatRoomAction();
        chatRoomAction = ChatRoomAction.builder().
                roomId(chatRoom.getRoomId()).roomName(chatRoom.getRoomName()).createDate(chatRoom.getCreateDate()).build();
        return chatRoomAction;
    }
}
