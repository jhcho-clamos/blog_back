package org.jhcho.blog.chat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public enum MessageData {
    ENTER("님이 입장하였습니다.");

    private String msgType;
}
