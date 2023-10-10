package com.damian.gs.service;

import com.damian.gs.dto.SuperDTO;
import com.damian.gs.response.Response;
import org.springframework.http.ResponseEntity;

public interface SuperService<T extends SuperDTO, ID> {
    ResponseEntity<Response> add(T t);

    ResponseEntity<Response> update(T t);

    ResponseEntity<Response> search(ID id);

    ResponseEntity<Response> delete(ID id);

    ResponseEntity<Response> getAll();

    ResponseEntity<Response> createAndSendResponse(int statusCode, String msg, Object data);
}
