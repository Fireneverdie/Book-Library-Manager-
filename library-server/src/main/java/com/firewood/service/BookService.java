package com.firewood.service;

import com.firewood.dto.BookDto;
import com.firewood.dto.BookPageQueryDto;
import com.firewood.entity.Book;
import com.firewood.result.PageResult;
import com.firewood.vo.BookVo;

import java.util.List;

public interface BookService {
    /**
     * 添加图书
     * @param bookDto
     */
    void add(BookDto bookDto);

    /**
     * 更新图书
     * @param bookVo
     */
    void update(BookVo bookVo);

    /**
     * 删除图书
     * @param id
     */
    void delete(Integer id);

    /**
     * 批量删除图书
     * @param ids
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 图书分页查询
     * @param bookPageQueryDto
     * @return
     */
    PageResult page(BookPageQueryDto bookPageQueryDto);

    /**
     * 获取图书详情
     * @param id
     * @return
     */
    BookVo detail(Integer id);

    List<Book> findByTypeId(Integer typeId);
}
