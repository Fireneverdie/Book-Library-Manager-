package com.firewood.service;

import com.firewood.entity.Admin;

public interface AdminService {
    /**
     * 根据用户名查询管理员账号
     * @param userName
     * @return
     */
    Admin getByUserName(String userName);
}
