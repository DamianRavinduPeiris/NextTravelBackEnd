package com.damian.ss.advisor;




import com.damian.ss.response.Response;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
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
        response.setMessage("Stripe Server threw an exception : " + exception.getLocalizedMessage());
        response.setData(null);
        return response;

    }
    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
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
