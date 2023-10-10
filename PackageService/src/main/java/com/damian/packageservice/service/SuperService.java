package com.damian.packageservice.service;

import com.damian.packageservice.dto.superdto.SuperDTO;
import com.damian.packageservice.response.Response;
import org.springframework.http.ResponseEntity;

public interface SuperService <T extends SuperDTO,ID>{
    ResponseEntity<Response>add(T t);
    ResponseEntity<Response>update(T t);
    ResponseEntity<Response>search(ID id);
    ResponseEntity<Response>delete(ID id);
    ResponseEntity<Response>getAll();
    ResponseEntity<Response>createAndSendResponse(int statusCode,String msg,Object data);
}
