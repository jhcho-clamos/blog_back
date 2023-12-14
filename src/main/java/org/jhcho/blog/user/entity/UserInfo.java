package org.jhcho.blog.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@EntityListeners(AuditingEntityListener.class)
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


    @CreatedDate
    @Column(name = "createdate", updatable = false)
    private LocalDateTime createDate;

    @NotNull
    @Column(name = "name", nullable = true)
    private String name;

    @Builder
    public UserInfo(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }
}
