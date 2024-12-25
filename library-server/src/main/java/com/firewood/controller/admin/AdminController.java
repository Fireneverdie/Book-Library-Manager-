package com.firewood.controller.admin;

import com.firewood.constant.MessageConstant;
import com.firewood.dto.AdminLoginDto;
import com.firewood.entity.Admin;
import com.firewood.properties.JwtProperties;
import com.firewood.result.Result;
import com.firewood.service.AdminService;
import com.firewood.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Slf4j
@Tag(name = "管理端相关接口")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 管理员登录
     * @param adminLoginDto
     * @return
     */
    @PostMapping("/login")
    @Operation(summary = "管理员登录")
    public Result<String> login(@RequestBody AdminLoginDto adminLoginDto){
        log.info("管理员登录：{}",adminLoginDto);

        //根据用户名查询数据库，查看登录的账号是否存在
        Admin user = adminService.getByUserName(adminLoginDto.getUsername());
        if (user != null) {
            //存在，交给service判断密码是否正确
            if(!user.getPassword().equals(adminLoginDto.getPassword())){
                return Result.error(MessageConstant.PASSWORD_ERROR);
            }
        }else{
            //不存在，返回错误信息
            return Result.error(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        //封装管理员信息生成jwt令牌
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        claims.put("name",user.getUsername());
        claims.put("nickName",user.getNickName());

        String token = JwtUtils.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);


        return Result.success(token);
    }
}
