package com.aerenarslanoglu.ecommerce.handler;

import com.aerenarslanoglu.ecommerce.exceptions.ProductPurchaseException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductPurchaseException.class)
    public ResponseEntity<ErrorDetails> handle(ProductPurchaseException ex,
                                               HttpServletRequest request){
        var errors = new HashMap<String, String>();
        errors.put("Error message: ", ex.getMessage());
        return new ResponseEntity<>(
                new ErrorDetails(
                        errors,
                        request.getRequestURI()
                ),
                HttpStatus.BAD_REQUEST);
    }
}
