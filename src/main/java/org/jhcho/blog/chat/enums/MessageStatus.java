package org.jhcho.blog.chat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageStatus {
    ENTER("ENTER"), TALK("TALK"), EXIT("EXIT");

    private String type;
}
