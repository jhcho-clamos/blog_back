package org.jhcho.blog.chat.repository;

import org.jhcho.blog.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jpaChatMessageRepository")
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
