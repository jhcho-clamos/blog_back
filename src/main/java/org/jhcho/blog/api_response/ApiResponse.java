package org.jhcho.blog.api_response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jhcho.blog.entity.Message;
import org.jhcho.blog.entity.StatusEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@AllArgsConstructor
@Getter
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

    public ResponseEntity<Message> toResponseEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        Message messageEntity = Message.builder().code(code).message(message).data(data).build();
        return new ResponseEntity<>(messageEntity, headers, HttpStatus.valueOf(code));
    }

}



