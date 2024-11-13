package com.firewood.service;

import com.firewood.dto.RecordQueryDto;
import com.firewood.result.PageResult;

public interface RecordService {

    void borrow(Integer bookId);

    void returnBook(Integer id);

    PageResult page(RecordQueryDto recordQueryDto);
}
