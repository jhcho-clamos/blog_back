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
    private final UserInfoMapper um;

    @Transactional
    public UserInfoResponse findUser(UserInfoRequest userInfoRequest) {
        Optional<UserInfo> userInfo = ur.findById(userInfoRequest.getId());
        if (userInfo.isPresent()) {
            String encodePw = ps.encode(userInfoRequest.getPassword());
            if (ps.matches(encodePw, userInfo.get().getPassword())) {
                return new UserInfoResponse(userInfo.get());
            } else {
                return null;
            }
        }
        return null;
    }

    @Transactional
    public UserInfoResponse signup(UserInfoRequest userInfoRequest) {
        Optional<UserInfo> userExist = ur.findById(userInfoRequest.getId());
        if (userExist.isPresent()) {
            return null;
        }
        userInfoRequest.setPassword(ps.encode(userInfoRequest.getPassword()));
        try {
            return new UserInfoResponse(ur.save(userInfoRequest.toEntity()));
        } catch (Exception err) {
            return null;
        }
    }

    public UserInfoResponse loginUser(String id) {
        return um.getUser(id);
    }
}
