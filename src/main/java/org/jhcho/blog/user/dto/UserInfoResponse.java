package org.jhcho.blog.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jhcho.blog.user.entity.UserInfo;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserInfoResponse {
    private String id;
    private String name;
    private LocalDateTime createDate;

    public UserInfoResponse(UserInfo entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.createDate = entity.getCreateDate();
    }
}
