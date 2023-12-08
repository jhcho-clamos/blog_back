package org.jhcho.blog.user.controller;


import lombok.RequiredArgsConstructor;
import org.jhcho.blog.user.dto.UserInfoRequest;
import org.jhcho.blog.user.dto.UserInfoResponse;
import org.jhcho.blog.user.mapper.UserInfoMapper;
import org.jhcho.blog.user.service.UserInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping("/get/{id}")
    public UserInfoResponse getUserInfo(@PathVariable("id") String id) {
        return userInfoService.findUser(id);
    }

    @PostMapping("/signup")
    public UserInfoResponse getUserInfo(@RequestBody UserInfoRequest userInfoRequest) {
        return userInfoService.signup(userInfoRequest);
    }

    @GetMapping("/mapper/get/{id}")
    public UserInfoResponse getUserMapper(@PathVariable("id") String id) {
        return userInfoService.mapperUser(id);
    }
}
