package com.firewood.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/book")
@Tag(name = "管理端图图书管理接口")
public class BookController {


    @Operation(summary = "获取图书列表")
    @GetMapping
    public String list(){

        return "Hello World";
    }
}
