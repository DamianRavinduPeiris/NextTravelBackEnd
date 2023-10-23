package com.damian.es.service;

import com.damian.es.response.Response;
import org.springframework.http.ResponseEntity;

public interface SuperService <T,ID>{
    ResponseEntity<Response>getOTP(ID id);
    ResponseEntity<Response>createAndSendResponse(int statusCode,String message,Object data);

    void saveOTP(T t);


}
