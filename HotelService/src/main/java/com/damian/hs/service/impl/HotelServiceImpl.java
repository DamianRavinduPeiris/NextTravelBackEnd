package com.damian.hs.service.impl;

import com.damian.hs.dto.HotelDTO;
import com.damian.hs.entity.Hotel;
import com.damian.hs.repo.HotelRepo;
import com.damian.hs.response.Response;
import com.damian.hs.service.custom.HotelService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private HotelRepo hotelRepo;
    @Autowired
    private Response response;
    @GetMapping(path = "/returnFuck")
    public String getFuck(){
        return "Fuuuuuuuck!";
    }

    @Override
    public ResponseEntity<Response> add(HotelDTO hotelDTO) {
        if (search(hotelDTO.getHotelId()).getBody() == null) {
            hotelRepo.save(mapper.map(hotelDTO, Hotel.class));
            return createAndSendResponse(HttpStatus.CREATED.value(), "Hotel Successfully saved!", null);

        }
        throw new RuntimeException("Hotel Already Exists!");
    }

    @Override
    public ResponseEntity<Response> update(HotelDTO hotelDTO) {
        if (search(hotelDTO.getHotelId()).getBody().getData() == null) {
            throw new RuntimeException("Hotel Not Found!");

        }
        hotelRepo.save(mapper.map(hotelDTO, Hotel.class));
        return createAndSendResponse(HttpStatus.OK.value(), "Hotel Successfully updated!", null);

    }

    @Override
    public ResponseEntity<Response> search(String s) {
        Optional<Hotel> hotel = hotelRepo.findById(s);
        if (hotel.isPresent()) {
            return createAndSendResponse(HttpStatus.FOUND.value(), "Hotel Successfully retrieved!",mapper.map(hotel.get(),Hotel.class));

        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Hotel Not Found!", null);
    }

    @Override
    public ResponseEntity<Response> delete(String s) {
        if (search(s).getBody().getData() == null) {
            throw new RuntimeException("Hotel Not Found!");

        }
        hotelRepo.deleteById(s);
        return createAndSendResponse(HttpStatus.OK.value(), "Hotel Successfully deleted!", null);


    }

    @Override
    public ResponseEntity<Response> getAll() {
        List<Hotel> hotels = hotelRepo.findAll();
        if (!hotels.isEmpty()) {
            List<HotelDTO> hotelDTOS = new ArrayList<>();
            hotels.forEach((hotel) -> {
                hotelDTOS.add(mapper.map(hotel, HotelDTO.class));

            });
            return createAndSendResponse(HttpStatus.FOUND.value(), "Hotels Successfully retrieved!", hotelDTOS);

        }
        throw new RuntimeException("No Hotels Found!");

    }

    @Override
    public ResponseEntity<Response> createAndSendResponse(int statusCode, String msg, Object data) {
        response.setMessage(msg);
        response.setData(data);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(statusCode));

    }
}
