package com.firewood.service.impl;

import com.firewood.dto.UserLoginDto;
import com.firewood.dto.UserUpdateDto;
import com.firewood.entity.User;
import com.firewood.mapper.UserMapper;
import com.firewood.service.UserService;
import com.firewood.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByUserName(String userName) {
        return userMapper.getByUserName(userName);
    }


    public void register(UserLoginDto userLoginDto) {
        User user = new User();
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);

        BeanUtils.copyProperties(userLoginDto,user);
        userMapper.register(user);
    }

    @Override
    public UserVo info(Integer id) {
        UserVo userVo = new UserVo();
        User user = userMapper.info(id);
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }

    @Override
    public void update(UserUpdateDto userUpdateDto) {
        User user = new User();
        BeanUtils.copyProperties(userUpdateDto,user);

        LocalDateTime now = LocalDateTime.now();
        user.setUpdateTime(now);

        userMapper.update(user);

    }
}
