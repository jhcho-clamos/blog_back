package org.jhcho.blog.user.controller;


import lombok.RequiredArgsConstructor;
import org.jhcho.blog.api_response.ApiResponse;
import org.jhcho.blog.entity.Message;
import org.jhcho.blog.entity.StatusEnum;
import org.jhcho.blog.user.dto.UserInfoRequest;
import org.jhcho.blog.user.dto.UserInfoResponse;
import org.jhcho.blog.user.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<Message> getUserInfo(@RequestBody UserInfoRequest userInfoRequest) {
        UserInfoResponse userInfoResponse = userInfoService.signup(userInfoRequest);
        if (userInfoResponse != null) {
            return new ApiResponse(200, StatusEnum.OK.getCode(), userInfoResponse).toResponseEntity();
        } else {
            return new ApiResponse(400, "회원가입에 실패 하였습니다.", userInfoResponse).toResponseEntity();
        }

    }

    @PostMapping("/login")
    public ResponseEntity<Message> getUserMapper(@RequestBody UserInfoRequest userInfoRequest) {
        UserInfoResponse userInfoResponse = userInfoService.findUser(userInfoRequest);
        if (userInfoResponse != null) {
            return new ApiResponse(200, StatusEnum.OK.getCode(), userInfoResponse).toResponseEntity();
        } else {
            return new ApiResponse(400, "로그인에 실패 하였습니다.", userInfoResponse).toResponseEntity();
        }
    }
}
