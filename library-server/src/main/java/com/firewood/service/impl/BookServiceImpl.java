package com.firewood.service.impl;

import com.firewood.constant.MessageConstant;
import com.firewood.context.BaseContext;
import com.firewood.dto.BookDto;
import com.firewood.dto.BookPageQueryDto;
import com.firewood.entity.Book;
import com.firewood.entity.Type;
import com.firewood.exception.BookUpdateException;
import com.firewood.mapper.BookMapper;
import com.firewood.mapper.TypeMapper;
import com.firewood.result.PageResult;
import com.firewood.service.BookService;
import com.firewood.vo.BookVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 添加图书
     * @param bookDto
     */
    public void add(BookDto bookDto) {
        Book book = new Book();

        BeanUtils.copyProperties(bookDto,book);

        Integer userId = BaseContext.getCurrentId();
        LocalDateTime now = LocalDateTime.now();
        book.setCreateUser(userId);
        book.setCreateTime(now);
        book.setUploadTime(now);

        bookMapper.add(book);
    }

    /**
     * 更新图书
     * @param bookVo
     */
    public void update(BookVo bookVo) {
        String typeName = bookVo.getTypeName();

        Integer typeId = typeMapper.getTypeIdByTypeName(typeName);
        //如果图书类型不存在，无法更新
        if(typeId == null ){
            throw new BookUpdateException(MessageConstant.TYPE_NOT_EXIST);
        }
        Book book = new Book();
        BeanUtils.copyProperties(bookVo,book);

        Integer userId = BaseContext.getCurrentId();
        LocalDateTime now = LocalDateTime.now();
        book.setTypeId(typeId);
        book.setUpdateTime(now);
        book.setUpdateUser(userId);

        bookMapper.update(book);
    }

    /**
     * 删除图书
     * @param id
     */
    public void delete(Integer id) {
        //等完成图书借阅接口后再完善代码
        //TODO 如果改本图书有人借阅，则不能删

        bookMapper.delete(id);
    }

    /**
     * 批量删除图书
     * @param ids
     */
    public void deleteBatch(List<Long> ids) {
        //等完成图书借阅接口后再完善代码
        //TODO 如果改本图书有人借阅，则不能删

        bookMapper.deleteBatch(ids);
    }

    /**
     * 图书分页查询
     * @param bookPageQueryDto
     * @return
     */
    public PageResult page(BookPageQueryDto bookPageQueryDto) {

        PageHelper.startPage(bookPageQueryDto.getPageNum(),bookPageQueryDto.getPageSize());

        Page<Book> bookList = bookMapper.page(bookPageQueryDto);

        List<BookVo> bookVoList = new ArrayList<>();

        //封装bookVo
        for (Book item : bookList) {
            BookVo bookVo = change(item);
            bookVoList.add(bookVo);
        }
        return new PageResult(bookList.getTotal(),bookVoList);
    }

    /**
     * 获取图书详情
     * @param id
     * @return
     */
    public BookVo detail(Integer id) {
        Book book = bookMapper.detail(id);
        BookVo bookVo = change(book);
        return bookVo;
    }

    //将book对象封装成BookVo返回
    private BookVo change(Book book){
        Type type = typeMapper.getById(book.getTypeId());
        BookVo bookVo = new BookVo();

        BeanUtils.copyProperties(book,bookVo);
        bookVo.setTypeName(type.getTypeName());
        return bookVo;
    }
}
