package com.firewood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPageQueryDto {
    private Integer pageNum;
    private Integer pageSize;

    private String bookName;
    private String bookAuthor;
    private Integer typeId;
}
