package com.damian.as.service.superservice;

import com.damian.as.dto.superdto.SuperDTO;
import com.damian.as.response.Response;
import org.springframework.http.ResponseEntity;

public interface SuperService<T extends SuperDTO, ID> {
    ResponseEntity<Response> add(T t);

    ResponseEntity<Response> update(T t);

    ResponseEntity<Response> search(ID id);

    ResponseEntity<Response> delete(ID id);

    ResponseEntity<Response> getAll();

    ResponseEntity<Response> createAndSendResponse(int code, String message, Object object);
}
