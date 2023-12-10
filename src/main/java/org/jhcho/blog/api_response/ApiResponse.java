package org.jhcho.blog.api_response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jhcho.blog.entity.Message;
import org.jhcho.blog.entity.StatusEnum;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@Getter
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

    public Message ApiResponse(int code, String message, T data) {
        return Message.builder().code(code).message(message).data(data).build();
    }

}



