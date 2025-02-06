package io.active.pharmacy.base.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
@ToString
@NoArgsConstructor
public class ErrorResponse {

    //private Integer code;

    Map<String, String> errors;

    public ErrorResponse(String errorString) {
        //this.code = code;
        Map<String, String> errors = new HashMap<>();
        errors.put("error", errorString);
        this.errors = errors;
    }

    public ErrorResponse(Map<String, String> errors) {
        //this.code = code;
        this.errors = errors;
    }
}