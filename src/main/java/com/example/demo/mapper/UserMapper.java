package com.example.demo.mapper;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * Company: 3DXT
 * Author: Joker
 * DateTime: 2018/7/6 9:56
 **/
public interface UserMapper {
    User selectByUsername(@Param("username") String username);
    int insert(User user);
    void updateValidate(@Param("id") Long id, @Param("validate") Boolean isValidate);
}
