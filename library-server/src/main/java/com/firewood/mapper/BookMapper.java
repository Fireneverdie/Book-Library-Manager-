package com.firewood.mapper;

import com.firewood.dto.BookPageQueryDto;
import com.firewood.entity.Book;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {
    void add(Book book);

    void update(Book book);

    @Delete("delete from book where id = #{id}")
    void delete(Integer id);

    void deleteBatch(List<Integer> ids);
    Page<Book> page(BookPageQueryDto bookPageQueryDto);

    @Select("select * from book where id = #{id}")
    Book detail(Integer id);

    @Select("select * from book where type_id = #{typeId}")
    List<Book> findByTypeId(Integer typeId);

    @Select("select * from book")
    List<Book> list();
}
