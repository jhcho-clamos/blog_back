package org.jhcho.blog.user.repository;

import org.jhcho.blog.user.dto.UserInfoResponse;
import org.jhcho.blog.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
  
}
