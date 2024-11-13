package com.firewood.controller.admin;

import com.firewood.dto.RecordQueryDto;
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
}
