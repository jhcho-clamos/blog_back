package org.jhcho.blog.entity;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class Message<T> {
    private int code;
    private String message;
    private T data;
}
