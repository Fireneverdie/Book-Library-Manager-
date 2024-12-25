package com.firewood.service.impl;

import com.firewood.dto.TypeDto;
import com.firewood.entity.Book;
import com.firewood.entity.Type;
import com.firewood.exception.TypeDeleteException;
import com.firewood.mapper.BookMapper;
import com.firewood.mapper.TypeMapper;
import com.firewood.service.BookService;
import com.firewood.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.firewood.constant.MessageConstant.TYPE_CANOT_DELETE;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private BookMapper bookMapper;
    @Override
    public void add(TypeDto typeDto) {
        Type type = new Type();
        BeanUtils.copyProperties(typeDto,type);

        LocalDateTime now = LocalDateTime.now();
        type.setCreateTime(now);
        type.setCreateUser(0);

        typeMapper.add(type);

    }

    /**
     * 根据id获取新增信息
     * @param id
     * @return
     */
    public TypeDto getById(Integer id) {
        Type type = typeMapper.getById(id);
        TypeDto typeDto = new TypeDto();
        BeanUtils.copyProperties(type,typeDto);
        return typeDto;
    }

    /**
     * 更新类型
     * @param typeDto
     */
    public void update(TypeDto typeDto) {
        Type type = new Type();
        BeanUtils.copyProperties(typeDto,type);
        LocalDateTime now = LocalDateTime.now();
        type.setUpdateTime(now);
        type.setUpdateUser(0);
        typeMapper.update(type);
    }

    /**
     * 删除类型信息
     * @param id
     */
    public void delete(Integer id) {

        List<Book> bookList = bookMapper.findByTypeId(id);
        if(!bookList.isEmpty() && bookList != null){
            throw new TypeDeleteException(TYPE_CANOT_DELETE);
        }
        typeMapper.delete(id);
    }

    /**
     * 类型批量删除
     * @param ids
     * @return
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            List<Book> bookList = bookMapper.findByTypeId(id);
            if(!bookList.isEmpty() && bookList != null){
                throw new TypeDeleteException(TYPE_CANOT_DELETE);
            }
        }
        typeMapper.deleteBatch(ids);
    }
}
