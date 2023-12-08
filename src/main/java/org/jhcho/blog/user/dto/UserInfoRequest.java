package org.jhcho.blog.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.jhcho.blog.user.entity.UserInfo;

@Getter
@Setter
public class UserInfoRequest {

    @NotBlank(message = "아이디는 null일수 없습니다.")
    private String id;

    @NotBlank(message = "비밀번호는 null일수 없습니다.")
    private String password;

    @NotBlank(message = "이름은 null일수 없습니다.")
    private String name;

    public UserInfo toEntity() {
        return UserInfo.builder().id(id).password(password).name(name).build();
    }
}
