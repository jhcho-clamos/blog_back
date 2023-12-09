package org.jhcho.blog.user.controller;


import lombok.RequiredArgsConstructor;
import org.jhcho.blog.api_response.ApiResponse;
import org.jhcho.blog.entity.StatusEnum;
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
    @ResponseBody
    public ApiResponse<UserInfoResponse> getUserInfo(@RequestBody UserInfoRequest userInfoRequest) {
        UserInfoResponse userInfoResponse = userInfoService.signup(userInfoRequest);
        if(userInfoResponse != null){
            return new ApiResponse(StatusEnum.OK.getStatusCode(),StatusEnum.OK.getCode(), userInfoService.signup(userInfoRequest));
        }else{
            return new ApiResponse(StatusEnum.BAD_REQUEST.getStatusCode(),StatusEnum.BAD_REQUEST.getCode(),userInfoService.signup(userInfoRequest));
        }

    }

    @GetMapping("/mapper/get/{id}")
    public ApiResponse<UserInfoResponse> getUserMapper(@PathVariable("id") String id) {
        UserInfoResponse userInfoResponse = userInfoService.mapperUser(id);
        if(userInfoResponse != null){
            return new ApiResponse(StatusEnum.OK.getStatusCode(),StatusEnum.OK.getCode(),userInfoResponse);
        }else{
            return new ApiResponse(StatusEnum.BAD_REQUEST.getStatusCode(),"사용자를 찾을 수 없습니다.",userInfoResponse);
        }
    }
}
