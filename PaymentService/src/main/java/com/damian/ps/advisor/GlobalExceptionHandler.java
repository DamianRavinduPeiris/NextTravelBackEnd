package com.damian.ps.advisor;




import com.damian.ps.response.Response;
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
    public Response handleExceptions(Exception exception) {
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage("Payment Server threw an exception : " + exception.getLocalizedMessage());
        response.setData(null);
        return response;

    }

    /*Validation Exception Handling.*/

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Response handleExceptions(MethodArgumentNotValidException exception) {
        HashMap<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach((fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }));

        response.setMessage("Server threw an exception : " + exception.getLocalizedMessage());
        response.setData(errors);
        return response;

    }

}
