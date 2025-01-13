package com.firewood.mapper;

import com.firewood.entity.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TypeMapper {

    @Insert("insert into type(type_name, create_time, create_user) "+
            "values " +
            "(#{typeName},#{createTime},#{createUser})"
    )
    void add(Type type);

    @Select("select * from type where id = #{id}")
    Type getById(Integer id);

    @Update("update type set type_name = #{typeName},update_time = #{updateTime},update_user = #{updateUser} where id = #{id}")
    void update(Type type);

    @Delete("delete from type where id = #{id}")
    void delete(Integer id);

    void deleteBatch(List<Integer> ids);

    @Select("select id from type where type_name = #{typeName}")
    Integer getTypeIdByTypeName(String typeName);
    @Select("select * from type")

    List<Type> list();
}
