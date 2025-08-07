package com.hrd.jpa_hibernate_homework.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
        @NotBlank(message = "Name is required!!!")
        String name,

        @NotNull(message = "Price is required!!!")
        @Positive(message = "Price must be positive!!!")
        Double price,

        @NotNull(message = "Quantity is required!!!")
        @Min(value = 1, message = "Quantity has a minimum value of 1!!!")
        Integer quantity
) {}
