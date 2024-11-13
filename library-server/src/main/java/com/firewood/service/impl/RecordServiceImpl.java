package com.firewood.service.impl;

import com.firewood.constant.MessageConstant;
import com.firewood.constant.StatusConstant;
import com.firewood.context.BaseContext;
import com.firewood.dto.RecordQueryDto;
import com.firewood.entity.Book;
import com.firewood.entity.Record;
import com.firewood.entity.User;
import com.firewood.exception.BookCacheException;
import com.firewood.mapper.BookMapper;
import com.firewood.mapper.RecordMapper;
import com.firewood.mapper.UserMapper;
import com.firewood.result.PageResult;
import com.firewood.service.RecordService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private RecordMapper recordMapper;


    @Transactional
    public void borrow(Integer bookId) {
        //获取用户信息
        Integer userId = BaseContext.getCurrentId();
        User user = userMapper.getByID(userId);

        //查看库存
        Book book = bookMapper.detail(bookId);
        if(book.getBookNum() == 0){
            throw new BookCacheException(MessageConstant.CACHE_ZERO);
        }

        Record record = Record.builder()
                .bookId(bookId)
                .bookName(book.getBookName())
                .userId(userId)
                .userName(user.getUserName())
                .borrowTime(LocalDateTime.now())
                .status(StatusConstant.ON_LOAN)
                .build();

        //添加借阅记录
        recordMapper.add(record);

        //更新库存
        book.setBookNum(book.getBookNum() - 1);
        bookMapper.update(book);
    }


    @Transactional
    public void returnBook(Integer id) {
        //获取借阅的图书id
        Record record = recordMapper.getById(id);

        Integer bookId = record.getBookId();
        Book book = bookMapper.detail(bookId);
        book.setBookNum(book.getBookNum() + 1);
        //更新库存
        bookMapper.update(book);
        //更新借阅记录
        recordMapper.returnBook(StatusConstant.RETURNED,id,LocalDateTime.now());
    }


    public PageResult page(RecordQueryDto recordQueryDto) {
        PageHelper.startPage(recordQueryDto.getPageNum(),recordQueryDto.getPageSize());
        Page recordList = recordMapper.page(recordQueryDto);
        return new PageResult(recordList.getTotal(),recordList);
    }
}
