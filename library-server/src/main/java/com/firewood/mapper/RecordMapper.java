package com.firewood.mapper;

import com.firewood.dto.RecordQueryDto;
import com.firewood.entity.Record;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

@Mapper

public interface RecordMapper {
    @Insert("INSERT INTO record (user_id, user_name, book_id, book_name, borrow_time,status) " +
            "VALUE (#{userId},#{userName},#{bookId},#{bookName},#{borrowTime},#{status})")
    void add(Record record);

    @Select("SELECT * FROM record WHERE id = #{id}")
    Record getById(Integer id);

    @Update("UPDATE record SET status = #{status}, returned_time = #{returnedTime} WHERE id = #{id}")
    void returnBook(Integer status, Integer id, LocalDateTime returnedTime);

    Page page(RecordQueryDto recordQueryDto);
}
