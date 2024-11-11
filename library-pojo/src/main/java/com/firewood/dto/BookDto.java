package com.firewood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Integer id;
    private String bookName;
    private String bookIsbn;
    private String bookAuthor;
    private String bookPublish;
    private Integer typeId;
    private Integer bookPage;
    private Double bookPrice;
    private Integer bookNum;
}
