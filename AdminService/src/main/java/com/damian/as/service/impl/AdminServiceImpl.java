package com.damian.as.service.impl;

import com.damian.as.dto.AdminDTO;
import com.damian.as.entity.Admin;
import com.damian.as.repo.AdminRepo;
import com.damian.as.response.Response;
import com.damian.as.service.custom.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private Response response;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AdminRepo adminRepo;

    @Override
    public ResponseEntity<Response> add(AdminDTO adminDTO) {
        if (search(adminDTO.getAdminID()).getBody().getData() == null) {
            adminRepo.save(mapper.map(adminDTO, Admin.class));
            return createAndSendResponse(HttpStatus.CREATED.value(), "Admin Successfully Saved!", adminDTO);

        }
        throw new RuntimeException("Admin Already Exists!");
    }

    @Override
    public ResponseEntity<Response> update(AdminDTO adminDTO) {
        if (search(adminDTO.getAdminID()).getBody().getData() != null) {
            adminRepo.save(mapper.map(adminDTO, Admin.class));
            return createAndSendResponse(HttpStatus.OK.value(), "Admin Successfully Updated!", adminDTO);

        }
        throw new RuntimeException("Admin Not Found!");
    }

    @Override
    public ResponseEntity<Response> search(String s) {
        Optional<Admin> admin = adminRepo.findById(s);
        if (admin.isPresent()) {
            return createAndSendResponse(HttpStatus.FOUND.value(), "Admin Successfully retrieved!", admin.get());

        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Admin not found!", null);
    }

    @Override
    public ResponseEntity<Response> delete(String s) {
        if (search(s).getBody().getData() != null) {
            adminRepo.deleteById(s);
            return createAndSendResponse(HttpStatus.OK.value(), "Admin Successfully Deleted!", null);

        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Admin not found!", null);
    }

    @Override
    public ResponseEntity<Response> getAll() {
        List<Admin> adminsList = adminRepo.findAll();
        if (!adminsList.isEmpty()) {
            List<AdminDTO> adminDTOList = new ArrayList<>();
            adminsList.forEach((admin) -> {
                adminDTOList.add(mapper.map(admin, AdminDTO.class));

            });
            return createAndSendResponse(HttpStatus.FOUND.value(), "Admins Successfully retrieved!", adminDTOList);

        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Admins not found!", null);
    }

    @Override
    public ResponseEntity<Response> createAndSendResponse(int code, String message, Object object) {
        response.setMessage(message);
        response.setData(object);
        return new ResponseEntity<Response>(response, HttpStatusCode.valueOf(code));
    }
}

