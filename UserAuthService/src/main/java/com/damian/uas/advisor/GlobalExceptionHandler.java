package com.damian.uas.advisor;


import com.damian.uas.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public ResponseEntity<Response>handleExceptions(Exception exception){
        response.setMessage("Server threw an exception : "+exception.getLocalizedMessage());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<Response>handleExceptions(UsernameNotFoundException exception){
        response.setMessage("Server threw an exception : "+exception.getLocalizedMessage());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Response>handleExceptions(MethodArgumentNotValidException exception){
        HashMap<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach((fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }));

        response.setMessage("Server threw an exception : "+exception.getLocalizedMessage());
        response.setData(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

}
