package com.firewood.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordQueryDto {
    private Integer pageNum;
    private Integer pageSize;
    private String userName;
    private String bookName;
    private Integer status;
}
