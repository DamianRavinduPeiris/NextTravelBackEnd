package com.damian.hs.advisor;


import com.damian.hs.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
@CrossOrigin
public class GlobalExceptionHandler {
    @Autowired
    private Response response;

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Response> handleExceptions(Exception exception) {
        response.setMessage("Hotel Server threw an exception : " + exception.getLocalizedMessage());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /*Validation Exception Handling.*/

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Response> handleExceptions(MethodArgumentNotValidException exception) {
        HashMap<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach((fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }));

        response.setMessage("Server threw an exception : " + exception.getLocalizedMessage());
        response.setData(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

}
