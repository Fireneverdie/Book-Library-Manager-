package com.firewood.service.impl;

import com.firewood.entity.Admin;
import com.firewood.mapper.AdminMapper;
import com.firewood.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 根据用户名查询管理员账号
     * @param userName
     * @return
     */
    public Admin getByUserName(String userName) {
        return adminMapper.getByUserName(userName);
    }
}
