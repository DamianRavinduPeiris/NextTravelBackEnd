package com.damian.hs.endpoints;

import com.damian.hs.dto.HotelDTO;
import com.damian.hs.interfaces.PackageInterface;
import com.damian.hs.response.Response;
import com.damian.hs.service.custom.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:8080")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private PackageInterface packageInterface;
    @PostMapping(path = "/saveHotel",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>saveHotel(@RequestBody HotelDTO hotelDTO){
        hotelService.add(hotelDTO);
        return packageInterface.saveHotelID(hotelDTO.getPackageId(),hotelDTO.getHotelId());

    }
    @PutMapping(path = "/updateHotel",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>updateHotel(@RequestBody HotelDTO hotelDTO){
        return hotelService.update(hotelDTO);

    }
    @GetMapping(path = "/searchHotel",params = "hotelID",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>searchHotel(@RequestParam("hotelID")String hotelID ){
        return hotelService.search(hotelID);

    }
    @DeleteMapping(path = "/deleteHotel",params = "hotelID",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>deleteHotel(@RequestParam("hotelID")String hotelID ){
        ResponseEntity<Response> response = searchHotel(hotelID);
        HotelDTO hotelDTO = (HotelDTO) response.getBody().getData();
        hotelService.delete(hotelID);
         return packageInterface.deleteHotelID(hotelDTO.getPackageId(),hotelDTO.getHotelId());

    }
    @GetMapping(path = "/getAllHotels",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>getAllHotels(){
        return hotelService.getAll();

    }

}
