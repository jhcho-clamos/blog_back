package org.jhcho.blog.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "userinfo")
public class UserInfo {

    @Id
    @NotNull
    @Column(name = "id", nullable = true)
    private String id;

    @NotNull
    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "createdate")
    private LocalDateTime createDate = LocalDateTime.now();

    @NotNull
    @Column(name = "name", nullable = true)
    private String name;

    @Builder
    public UserInfo(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.createDate = createDate;
        this.name = name;
    }
}
