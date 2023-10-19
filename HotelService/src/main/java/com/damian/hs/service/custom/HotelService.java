package com.damian.hs.service.custom;

import com.damian.hs.dto.HotelDTO;
import com.damian.hs.entity.Hotel;
import com.damian.hs.response.Response;
import com.damian.hs.service.SuperService;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface HotelService extends SuperService<HotelDTO,String> {
    ResponseEntity<Response>deleteAllHotels(List<String> hotelIDList);
    ResponseEntity<Response> findByHotelName(String hotelName);
}
