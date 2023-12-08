package org.jhcho.blog.user.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jhcho.blog.user.dto.UserInfoResponse;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface UserInfoMapper {
    UserInfoResponse getUser(@Param("id") String id);
}
