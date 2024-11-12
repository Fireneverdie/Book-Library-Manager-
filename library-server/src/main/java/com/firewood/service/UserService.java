package com.firewood.service;

import com.firewood.dto.UserLoginDto;
import com.firewood.dto.UserUpdateDto;
import com.firewood.entity.User;
import com.firewood.vo.UserVo;

public interface UserService {

    User getByUserName(String userName);

    void register(UserLoginDto userLoginDto);

    UserVo info(Integer id);

    void update(UserUpdateDto userUpdateDto);
}
