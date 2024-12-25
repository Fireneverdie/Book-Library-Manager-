package com.firewood.controller.admin;

import com.firewood.dto.TypeDto;
import com.firewood.result.Result;
import com.firewood.service.TypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin/type")
@Slf4j
@Tag(name = "类型相关接口")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @PostMapping
    @Operation(summary = "新增类型")
    public Result add(@RequestBody TypeDto typeDto){
        log.info("新增类型:{}",typeDto);
        typeService.add(typeDto);
        return Result.success();
    }

    /**
     * 通过id获取类型信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据id获取类型信息")
    public Result<TypeDto> getById(@PathVariable Integer id){
        TypeDto typeDto = typeService.getById(id);
        return Result.success(typeDto);
    }

    /**
     * 更新类型信息
     * @param typeDto
     * @return
     */
    @PutMapping
    @Operation(summary = "更新类型信息")
    public Result update(@RequestBody TypeDto typeDto){
        log.info("更新类型信息，将类型更改为:{}",typeDto.getTypeName());
        typeService.update(typeDto);
        return Result.success();
    }

    /**
     * 删除类型
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除类型")
    public Result delete(@PathVariable Integer id){
        typeService.delete(id);
        return Result.success();
    }

    /**
     * 类型批量删除
     * @param ids
     * @return
     */
    @DeleteMapping
    @Operation(summary = "类型批量删除")
    public Result delete(@RequestParam List<Integer> ids){
        log.info("类型批量删除:{}",ids);
        typeService.deleteBatch(ids);
        return Result.success();
    }

}
