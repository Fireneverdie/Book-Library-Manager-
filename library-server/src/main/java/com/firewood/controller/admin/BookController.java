package com.firewood.controller.admin;

import com.firewood.dto.BookDto;
import com.firewood.dto.BookPageQueryDto;
import com.firewood.entity.Book;
import com.firewood.result.PageResult;
import com.firewood.result.Result;
import com.firewood.service.BookService;
import com.firewood.vo.BookVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("adminBookController")
@RequestMapping("/admin/book")
@Slf4j
@Tag(name = "管理端图图书管理接口")
public class BookController {

    @Autowired
    private BookService bookService;


    @Operation(summary = "获取图书列表")
    @GetMapping
    public Result<List<Book>> list(){
        List<Book> list = bookService.list();
        return Result.success(list);
    }

    @PostMapping
    @Operation(summary = "添加图书")
    public Result add(@RequestBody BookDto bookDto){
        log.info("添加图书：{}",bookDto);
        bookService.add(bookDto);
        return Result.success();
    }


    @PutMapping
    @Operation(summary = "修改图书")
    public Result update(@RequestBody BookVo bookVo){
        log.info("修改图书：{}",bookVo);
        bookService.update(bookVo);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除图书")
    public Result delete(@PathVariable Integer id){
        log.info("删除图书，要删除的图书id为：{}",id);
        bookService.delete(id);
        return Result.success();
    }

    @DeleteMapping
    @Operation(summary = "批量删除图书")
    public Result deleteBatch(@RequestParam List<Integer> ids){
        log.info("图书信息批量删除:{}",ids);
        bookService.deleteBatch(ids);
        return Result.success();
    }

    @GetMapping("/page")
    @Operation(summary = "图书分页查询")
    public Result<PageResult> page(BookPageQueryDto bookPageQueryDto){
        log.info("图书分页查询：{}",bookPageQueryDto);
        PageResult pageResult = bookService.page(bookPageQueryDto);
        return Result.success(pageResult);
    }

    @GetMapping("/detail")
    @Operation(summary = "获取图书详情")
    public Result<BookVo> getById(Integer id){
        log.info("根据图书id获取图书详情：id为{}",id);
        BookVo bookVo = bookService.detail(id);
        return Result.success(bookVo);
    }

    @GetMapping("/typeId")
    @Operation(summary = "根据类型id查找图书")
    Result<List<Book>> findByTypeId(Integer typeId){
        log.info("根据类型id获取图书详情：id为{}",typeId);
        List<Book> bookList = bookService.findByTypeId(typeId);
        return Result.success(bookList);

    }








}
