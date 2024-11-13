package com.firewood.controller.user;

import com.firewood.dto.BookPageQueryDto;
import com.firewood.result.PageResult;
import com.firewood.result.Result;
import com.firewood.service.BookService;
import com.firewood.vo.BookVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("userBookController")
@RequestMapping("/user/book/")
@Tag(name = "用户端图书接口")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/page")
    @Operation(summary = "图书分页查询")
    public Result<PageResult> page(BookPageQueryDto bookPageQueryDto){
        log.info("图书分页查询：{}",bookPageQueryDto);
        PageResult pageResult = bookService.page(bookPageQueryDto);
        return Result.success(pageResult);
    }

    @GetMapping("/detail")
    @Operation(summary = "获取图书详情")
    public Result<BookVo> getById(Integer id) {
        log.info("根据图书id获取图书详情：id为{}", id);
        BookVo bookVo = bookService.detail(id);
        return Result.success(bookVo);
    }
}
