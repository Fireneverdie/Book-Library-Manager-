package com.firewood.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/book")
public class BookController {


    @GetMapping
    public String list(){

        return "Hello World";
    }
}
