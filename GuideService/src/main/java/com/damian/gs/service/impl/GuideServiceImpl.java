package com.damian.gs.service.impl;

import com.damian.gs.dto.GuideDTO;
import com.damian.gs.entity.Guide;
import com.damian.gs.repo.GuideRepo;
import com.damian.gs.response.Response;
import com.damian.gs.service.custom.GuideService;
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
public class GuideServiceImpl implements GuideService {
    @Autowired
    private GuideRepo guideRepo;
    @Autowired
    private Response response;
    @Autowired
    private ModelMapper mapper;

    @Override
    public ResponseEntity<Response> add(GuideDTO guideDTO) {

        if (search(guideDTO.getGuideId()).getBody().getData() == null) {
            guideDTO.setGuideImageLocation(guideDTO.getGuideImageLocation().replace("\\", "/"));
            guideRepo.save(mapper.map(guideDTO, Guide.class));
            return createAndSendResponse(HttpStatus.CREATED.value(), "Guide saved successfully!", null);

        }
        return createAndSendResponse(HttpStatus.CONFLICT.value(), "Guide already exists!", null);
    }

    @Override
    public ResponseEntity<Response> update(GuideDTO guideDTO) {
        if (search(guideDTO.getGuideId()).getBody().getData() != null) {
            guideDTO.setGuideImageLocation(guideDTO.getGuideImageLocation().replace("\\", "/"));
            guideRepo.save(mapper.map(guideDTO, Guide.class));
            return createAndSendResponse(HttpStatus.OK.value(), "Guide updated successfully!", null);


        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Guide not found!", null);

    }

    @Override
    public ResponseEntity<Response> search(String s) {
        Optional<Guide> guide = guideRepo.findById(s);
        if (guide.isPresent()) {
            return createAndSendResponse(HttpStatus.OK.value(), "Guide retrieved successfully!", mapper.map(guide.get(), GuideDTO.class));

        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Guide not found!", null);


    }

    @Override
    public ResponseEntity<Response> delete(String s) {

        if (search(s).getBody().getData() != null) {
            guideRepo.deleteById(s);
            return createAndSendResponse(HttpStatus.OK.value(), "Guide deleted successfully!", null);
        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Guide not found!", null);
    }

    @Override
    public ResponseEntity<Response> getAll() {
        List<Guide> guidesList = guideRepo.findAll();
        if (guidesList.isEmpty()) {
            throw new RuntimeException("No guides found!");

        }
        List<GuideDTO> guideDTOList = new ArrayList<>();
        guidesList.forEach((guide) -> {
            guideDTOList.add(mapper.map(guide, GuideDTO.class));

        });
        return createAndSendResponse(HttpStatus.OK.value(), "Guides retrieved successfully!", guideDTOList);

    }

    @Override
    public ResponseEntity<Response> createAndSendResponse(int statusCode, String msg, Object data) {
        response.setStatusCode(statusCode);
        response.setMessage(msg);
        response.setData(data);
        return new ResponseEntity<Response>(response, HttpStatusCode.valueOf(statusCode));
    }

    @Override
    public ResponseEntity<Response> searchByGuideName(String guideName) {
        Optional<Guide> guide = guideRepo.findByGuideName(guideName);
        if (guide.isPresent()) {
            System.out.println("Guide Name : " + guideName);
            System.out.println("Guide : " + guide.get());
            return createAndSendResponse(HttpStatus.OK.value(), "Guide retrieved successfully!", mapper.map(guide.get(), GuideDTO.class));

        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Guide not found!", null);
    }
}
