package com.hrd.jpa_hibernate_homework.model.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponseAPI<T>{
    private List<T> items;
    private Pagination pagination;
}
