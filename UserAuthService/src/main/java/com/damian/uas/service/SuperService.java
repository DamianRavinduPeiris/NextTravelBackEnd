package com.damian.uas.service;


import com.damian.uas.dto.superdto.SuperDTO;
import com.damian.uas.response.Response;
import org.springframework.http.ResponseEntity;

public interface SuperService<T extends SuperDTO, ID> {
    ResponseEntity<Response> add(T t);

    ResponseEntity<Response> update(T t);

    ResponseEntity<Response> delete(ID id);

    ResponseEntity<Response> search(ID id);

    ResponseEntity<Response> getAll(T t);


}
