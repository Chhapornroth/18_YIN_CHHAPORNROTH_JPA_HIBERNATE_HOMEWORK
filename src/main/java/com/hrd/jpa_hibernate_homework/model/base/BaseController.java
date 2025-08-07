package com.hrd.jpa_hibernate_homework.model.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

public abstract class BaseController {
    protected <T> ResponseEntity<ResponseAPI<T>> response(){
        return ResponseEntity.ok().build();
    }

    protected <T> ResponseEntity<ResponseAPI<T>> response(ResponseAPI<T> response){
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    public ResponseEntity<ProblemDetail> problemDetailResponseEntity(Object errors, HttpStatus status){
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("details", errors);
        return new ResponseEntity<>(problemDetail, status);
    }
}
