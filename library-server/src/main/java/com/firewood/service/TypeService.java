package com.firewood.service;

import com.firewood.dto.TypeDto;

import java.util.List;

public interface TypeService {


    /**
     * 新增类型
     * @param typeDto
     */
    void add(TypeDto typeDto);

    /**
     * 根据id获取新增信息
     * @param id
     * @return
     */
    TypeDto getById(Integer id);

    /**
     * 更新类型
     * @param typeDto
     */
    void update(TypeDto typeDto);

    /**
     * 删除类型信息
     * @param id
     */
    void delete(Integer id);

    /**
     * 类型批量删除
     * @param ids
     * @return
     */
    void deleteBatch(List<Integer> ids);
}
