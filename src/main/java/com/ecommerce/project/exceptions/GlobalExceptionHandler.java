package com.ecommerce.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<Object, Object> handleCategoryBlankNameError(MethodArgumentNotValidException e) {
        HashMap<Object, Object> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(err -> {
            response.put("message", err.getDefaultMessage());
        });
        return response;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public HashMap<String , String> handleResourceNotFoundException(ResourceNotFoundException e){
        HashMap<String , String> response = new HashMap<>();
        response.put("message" , e.getMessage());
        return response;
    }

    @ExceptionHandler(APIException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public HashMap<String , String> handleAPIException(APIException e){
        HashMap<String , String> response = new HashMap<>();
        response.put("message" , e.getMessage());
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, String> handleGlobalExceptions(Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "An unexpected error occurred.");
        // Avoid including stack trace details in the response
        response.put("details", "Please contact support if the issue persists.");
        return response;
    }
}