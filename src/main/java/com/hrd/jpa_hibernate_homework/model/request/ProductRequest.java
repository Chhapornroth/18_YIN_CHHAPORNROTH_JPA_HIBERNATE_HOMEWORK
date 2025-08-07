package com.hrd.jpa_hibernate_homework.model.request;

public record ProductRequest(
        String name,
        Double price,
        Integer quantity
) {}
