package org.jhcho.blog.user.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jhcho.blog.user.dto.UserInfoRequest;
import org.jhcho.blog.user.dto.UserInfoResponse;
import org.jhcho.blog.user.entity.UserInfo;
import org.jhcho.blog.user.mapper.UserInfoMapper;
import org.jhcho.blog.user.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserInfoRepository ur;
    private final PasswordEncoder ps;
    @Autowired
    private final UserInfoMapper um;

    @Transactional
    public UserInfoResponse findUser(String id) {
        Optional<UserInfo> userInfo = ur.findById(id);
        if (userInfo.isPresent()) {
            return new UserInfoResponse(userInfo.get());
        }
        return null;
    }

    @Transactional
    public UserInfoResponse signup(UserInfoRequest userInfoRequest) {
        userInfoRequest.setPassword(ps.encode(userInfoRequest.getPassword()));
        Optional<UserInfo> userInfo = Optional.of(ur.save(userInfoRequest.toEntity()));
        if (userInfo.isPresent()) {
            return new UserInfoResponse(userInfo.get());
        }
        return null;
    }

    public UserInfoResponse mapperUser(String id) {
        return um.getUser(id);
    }
}
