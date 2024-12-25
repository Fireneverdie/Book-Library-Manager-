package com.firewood.service;

import com.firewood.dto.RecordQueryDto;
import com.firewood.entity.Record;
import com.firewood.result.PageResult;

import java.util.List;

public interface RecordService {

    void borrow(Integer bookId);

    void returnBook(Integer id);

    PageResult page(RecordQueryDto recordQueryDto);

    /**
     * 借阅记录分页查询
     * @param recordQueryDto
     * @return
     */
    List<Record> find(RecordQueryDto recordQueryDto);
}
