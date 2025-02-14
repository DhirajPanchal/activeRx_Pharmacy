package io.active.pharmacy.base.exception;


import io.active.pharmacy.base.dto.CustomErrorResponse;
import lombok.Getter;


public class CustomErrorException extends RuntimeException {
    @Getter
    private CustomErrorResponse errorResponse;

    public CustomErrorException(String message, CustomErrorResponse errorResponse) {
        super(message);
        errorResponse.setMessage(message);
        this.errorResponse = errorResponse;
    }
}