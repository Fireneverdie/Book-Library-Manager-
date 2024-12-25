package com.firewood.controller.admin;

import com.firewood.dto.RecordQueryDto;
import com.firewood.entity.Record;
import com.firewood.result.PageResult;
import com.firewood.result.Result;
import com.firewood.service.RecordService;
import com.github.pagehelper.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController("adminRecordController")
@RequestMapping("/admin/record")
@Tag(name = "管理端借阅相关接口")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @GetMapping("/page")
    @Operation(summary = "借阅记录分页查询")
    public Result<PageResult> page(RecordQueryDto recordQueryDto){
        log.info("借阅记录分页查询,参数：{}",recordQueryDto);
        PageResult pageResult = recordService.page(recordQueryDto);
        return Result.success(pageResult);
    }

    @GetMapping("/find")
    @Operation(summary = "借阅记录条件查询")
    public Result<List<Record>> find(RecordQueryDto recordQueryDto){
        log.info("借阅条件查询,参数：{}",recordQueryDto);
        List<Record> recordList = recordService.find(recordQueryDto);
        return Result.success(recordList);
    }
}
