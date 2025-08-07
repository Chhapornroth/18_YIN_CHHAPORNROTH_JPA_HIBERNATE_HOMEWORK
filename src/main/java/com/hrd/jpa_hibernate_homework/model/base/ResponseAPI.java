package com.hrd.jpa_hibernate_homework.model.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAPI <T>{
    private String message;
    private T payload;
    private HttpStatus status;
    @Builder.Default
    private Instant instant = Instant.now();
}
