package com.firewood.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
    @Pattern(regexp = "^\\S{2,12}$",message = "用户名长度必须是2~12字符")
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    @Pattern(regexp = "^\\S{6,12}$",message = "密码的长度必须是6~18个字符")
    @NotEmpty(message = "密码不能为空")
    private String password;
}
