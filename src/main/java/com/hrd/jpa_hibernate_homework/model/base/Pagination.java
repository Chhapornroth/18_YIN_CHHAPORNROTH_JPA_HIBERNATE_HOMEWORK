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
    private int totalElements;
    private int currentPage;
    private int pageSize;
    private int totalPages;
}
