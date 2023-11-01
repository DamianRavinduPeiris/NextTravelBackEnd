package com.damian.ps.service;

import com.damian.ps.dto.superdto.SuperDTO;
import com.damian.ps.response.Response;
import org.springframework.http.ResponseEntity;

public interface SuperService <T extends SuperDTO,ID>{
    ResponseEntity<Response>add(T t);
    ResponseEntity<Response>update(T t);
    ResponseEntity<Response>search(ID id);
    ResponseEntity<Response>delete(ID id,ID uId,ID pdId);
    ResponseEntity<Response>getAll();
    ResponseEntity<Response>createAndSendResponse(int code,String message,Object data);

}
