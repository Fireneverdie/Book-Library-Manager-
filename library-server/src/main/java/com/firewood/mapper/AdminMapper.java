package com.firewood.mapper;

import com.firewood.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    @Select("select * from admin where user_name = #{userName}")
    Admin getByUserName(String userName);
}
