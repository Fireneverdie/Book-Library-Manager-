package com.firewood.mapper;

import com.firewood.dto.UserLoginDto;
import com.firewood.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from user where user_name = #{userName}")
    User getByUserName(String userName);


    @Insert("insert into user (user_name, create_time, password) values (#{userName},#{createTime},#{password})")
    void register(User user);

    @Select("select * from user where id = #{id}")
    User info(Integer id);


    void update(User user);

    @Select("SELECT * FROM user WHERE id = #{userId}")
    User getByID(Integer userId);
}
