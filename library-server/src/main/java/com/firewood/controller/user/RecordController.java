package com.firewood.controller.user;

import com.firewood.result.Result;
import com.firewood.service.BookService;
import com.firewood.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("userRecordController")
@RequestMapping("/user/record")
@Tag(name = "用户借阅接口")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @GetMapping("/borrow")
    @Operation(summary = "图书借阅")
    public Result borrow(Integer bookId){
        log.info("图书借阅...借阅的图书id为：{}",bookId);
        recordService.borrow(bookId);
        return Result.success();
    }

    @GetMapping("/return/{id}")
    @Operation(summary = "归还图书")
    public Result returnBook(@PathVariable Integer id){
        log.info("归还图书...");
        recordService.returnBook(id);
        return Result.success();
    }


}
