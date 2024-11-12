package com.firewood.controller.user;

import com.firewood.constant.MessageConstant;
import com.firewood.dto.UserLoginDto;
import com.firewood.dto.UserUpdateDto;
import com.firewood.entity.User;
import com.firewood.properties.JwtProperties;
import com.firewood.result.PageResult;
import com.firewood.result.Result;
import com.firewood.service.UserService;
import com.firewood.utils.JwtUtils;
import com.firewood.vo.UserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
@Tag(name = "用户端接口")
@Validated
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    @Operation(summary = "用户登录接口")
    public Result<String> login(@RequestBody @Valid UserLoginDto userLoginDto){
        log.info("用户登录:{}",userLoginDto);
        User user = userService.getByUserName(userLoginDto.getUserName());
        if(user == null){
            return Result.error(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        if(user.getPassword().equals(userLoginDto.getPassword())){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",user.getId());
            claims.put("username",user.getUserName());
            String token = JwtUtils.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
            return Result.success(token);
        }
        return Result.error(MessageConstant.PASSWORD_ERROR);
    }

    @PostMapping("/register")
    @Operation(summary = "用户注册接口")
    public Result register(@RequestBody @Valid UserLoginDto userLoginDto){
        log.info("用户名不能为空：{}",userLoginDto);
        User user = userService.getByUserName(userLoginDto.getUserName());

        if(user == null){
            userService.register(userLoginDto);
            return Result.success();
        }
        return Result.error(MessageConstant.REGISTER_FAILED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取用户信息")
    public Result info(@PathVariable Integer id){
        log.info("获取用户信息，用户id为：{}",id);
        UserVo userVo = userService.info(id);
        return Result.success(userVo);
    }


    @PutMapping
    @Operation(summary = "更新用户信息")
    public Result update(@RequestBody @Valid UserUpdateDto userUpdateDto){
        log.info("更新用户信息：{}",userUpdateDto);
        String phone = userUpdateDto.getPhone();
        String nickName = userUpdateDto.getNickName();
        if(!Pattern.matches("^[0-9]{11}$", phone)){
            return Result.error("电话号码的格式或长度错误");
        }

        if(!nickName.isBlank() && !Pattern.matches("^\\S{2,12}$", nickName)){
            return Result.error("昵称的长度必须是2~12个字符");
        }
        userService.update(userUpdateDto);
        return Result.success();
    }

}
