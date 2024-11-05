package com.firewood.service.impl;

import com.firewood.dto.TypeDto;
import com.firewood.entity.Type;
import com.firewood.mapper.TypeMapper;
import com.firewood.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;
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
        //TODO 先根据这个id查询图书信息，如果有图书是这个类型则不能删除这个类型

        typeMapper.delete(id);
    }

    /**
     * 类型批量删除
     * @param ids
     * @return
     */
    public void deleteBatch(List<Long> ids) {
        //TODO 先根据这个id查询图书信息，如果有图书是这个类型则不能删除这个类型
        typeMapper.deleteBatch(ids);
    }
}
