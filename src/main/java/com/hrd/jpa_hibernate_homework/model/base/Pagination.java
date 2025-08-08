package com.hrd.jpa_hibernate_homework.model.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {
    private Integer totalElements;
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalPages;
}
