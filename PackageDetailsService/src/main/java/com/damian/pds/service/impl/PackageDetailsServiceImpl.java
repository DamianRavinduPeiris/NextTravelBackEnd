package com.damian.pds.service.impl;

import com.damian.pds.dto.PackageDetailsDTO;
import com.damian.pds.entity.PackageDetails;
import com.damian.pds.interfaces.UserInterface;
import com.damian.pds.repo.PackageDetailsRepo;
import com.damian.pds.response.Response;
import com.damian.pds.service.custom.PackageDetailsService;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.UUIDGenerator;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PackageDetailsServiceImpl implements PackageDetailsService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private Response response;
    @Autowired
    private PackageDetailsRepo packageDetailsRepo;

    @Autowired
    private UserInterface userInterface;

    

    @Override
    public ResponseEntity<Response> add(PackageDetailsDTO packageDetailsDTO) {
        if(search(packageDetailsDTO.getPackageDetailsId()).getBody().getData() == null){
            packageDetailsDTO.setPackageDetailsId(generateId());
            packageDetailsRepo.save(mapper.map(packageDetailsDTO, PackageDetails.class));
            System.out.println("USER ID "+packageDetailsDTO.getUserId());
            System.out.println("PACKAGE DETAILS  ID "+packageDetailsDTO.getPackageDetailsId());
            userInterface.updatePackageDetailsID(packageDetailsDTO.getUserId(),packageDetailsDTO.getPackageDetailsId());
            return createAndSendResponse(HttpStatus.CREATED.value(), "PackageDetails Created Successfully!",null);

        }
        return createAndSendResponse(HttpStatus.CONFLICT.value(), "PackageDetails Already Exists!",null);

    }

    @Override
    public ResponseEntity<Response> update(PackageDetailsDTO packageDetailsDTO) {

        if(search(packageDetailsDTO.getPackageDetailsId()).getBody().getData() != null){
            packageDetailsRepo.save(mapper.map(packageDetailsDTO, PackageDetails.class));
            return createAndSendResponse(HttpStatus.OK.value(), "PackageDetails Updated Successfully!",null);

        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "PackageDetails Not Found!",null);
    }

    @Override
    public ResponseEntity<Response> search(String s) {
        Optional<PackageDetails> pack = packageDetailsRepo.findById(s);
        if(pack.isPresent()){
            return createAndSendResponse(HttpStatus.FOUND.value(), "PackageDetails Retrieved successfully!",mapper.map(pack.get(),PackageDetailsDTO.class));

        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "PackageDetails Not Found!",null);


    }

    @Override
    public ResponseEntity<Response> delete(String s) {
        if(search(s).getBody().getData() == null){
            return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "PackageDetails Not Found!",null);

        }
        packageDetailsRepo.deleteById(s);
        return createAndSendResponse(HttpStatus.OK.value(), "PackageDetails Deleted Successfully!",null);


    }

    @Override
    public ResponseEntity<Response> getAll() {
        List<PackageDetails> packs = packageDetailsRepo.findAll();
        if(packs.isEmpty()){
            return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "PackageDetails Not Found!",null);

        }
        List<PackageDetailsDTO> packageDetailsDTOList = new ArrayList<>();
        packs.forEach((p)->{
            packageDetailsDTOList.add(mapper.map(p,PackageDetailsDTO.class));

        });
        return createAndSendResponse(HttpStatus.FOUND.value(), "PackageDetails Retrieved Successfully!",packageDetailsDTOList);


    }

    @Override
    public ResponseEntity<Response> createAndSendResponse(int statusCode, String msg, Object data) {
        response.setStatusCode(statusCode);
        response.setMessage(msg);
        response.setData(data);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(statusCode));
    }

    @Override
    public ResponseEntity<Response> getPackageDetailsByUser(String userId) {
        List<PackageDetails> packs = packageDetailsRepo.findByUserId(userId);
        if(packs.isEmpty()){
            return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "PackageDetails Not Found!",null);

        }
        List<PackageDetailsDTO>packageDetailsDTOList = new ArrayList<>();
        packs.forEach((p)->{
            packageDetailsDTOList.add(mapper.map(p,PackageDetailsDTO.class));

        });

        return createAndSendResponse(HttpStatus.FOUND.value(), "PackageDetails Retrieved Successfully!",packageDetailsDTOList);

    }

    @Override
    public ResponseEntity<Response> deletePackageDetailsByUser(String userId) {
        List<PackageDetails> packs = packageDetailsRepo.findByUserId(userId);
        if(packs.isEmpty()){
            return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "No PackageDetails found to delete!",null);

        }
        packs.forEach((p)->{
            packageDetailsRepo.deleteById(p.getPackageDetailsId());

        });
        return createAndSendResponse(HttpStatus.OK.value(), "PackageDetails Deleted Successfully!",null);

    }

    @Override
    public String generateId() {
        return  "NEXT" +  Generators.randomBasedGenerator().generate().toString();
    }
}
