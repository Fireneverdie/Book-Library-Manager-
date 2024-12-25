package com.firewood.service.impl;

import com.firewood.constant.MessageConstant;
import com.firewood.constant.StatusConstant;
import com.firewood.context.BaseContext;
import com.firewood.dto.BookDto;
import com.firewood.dto.BookPageQueryDto;
import com.firewood.dto.RecordQueryDto;
import com.firewood.entity.Book;
import com.firewood.entity.Record;
import com.firewood.entity.Type;
import com.firewood.exception.BookAddException;
import com.firewood.exception.BookDeleteException;
import com.firewood.exception.BookUpdateException;
import com.firewood.mapper.BookMapper;
import com.firewood.mapper.RecordMapper;
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

import static com.firewood.constant.MessageConstant.BOOK_CANOT_DELETE;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 添加图书
     * @param bookDto
     */
    public void add(BookDto bookDto) {
        //如果添加的小说类型不存在，添加失败
        Type type = typeMapper.getById(bookDto.getTypeId());
        if(type == null){
            throw new BookAddException(MessageConstant.TYPE_NOT_EXIST);
        }

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

        if(!judgeIsReturned(id)) throw new BookDeleteException(BOOK_CANOT_DELETE);
        bookMapper.delete(id);
    }

    /**
     * 批量删除图书
     * @param ids
     */
    public void deleteBatch(List<Integer> ids) {
        //等完成图书借阅接口后再完善代码
        for (Integer id : ids) {
            if(!judgeIsReturned(id)) throw new BookDeleteException(BOOK_CANOT_DELETE);
        }
        bookMapper.deleteBatch(ids);
    }

    /**
     * 图书分页查询
     * @param bookPageQueryDto
     * @return
     */
    public PageResult page(BookPageQueryDto bookPageQueryDto) {

        //开启分页查询
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

    /**
     * 根据类型id获取图书详情
     * @param typeId
     * @return
     */
    public List<Book> findByTypeId(Integer typeId) {
        return bookMapper.findByTypeId(typeId);
    }

    //将book对象封装成BookVo返回
    private BookVo change(Book book){
        Type type = typeMapper.getById(book.getTypeId());
        BookVo bookVo = new BookVo();

        BeanUtils.copyProperties(book,bookVo);
        bookVo.setTypeName(type.getTypeName());
        return bookVo;
    }

    //查看该本图书是否正在借阅
    //判断该本书是否在借阅

    private boolean judgeIsReturned(Integer bookId){
        RecordQueryDto recordQueryDto = RecordQueryDto.builder()
                .bookId(bookId)
                .build();

        Page page = recordMapper.page(recordQueryDto);
        List<Record> recordList = page.getResult();
        if (recordList != null && !recordList.isEmpty()) {
            for (Record record : recordList) {
                if(record.getStatus() == StatusConstant.ON_LOAN){
                    //表示有人在借
                    return false;
                }
            }
        }
        //ture该本图书没人借
        return true;

    }
}
