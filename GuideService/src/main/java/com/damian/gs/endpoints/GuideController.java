package com.damian.gs.endpoints;

import com.damian.gs.dto.GuideDTO;
import com.damian.gs.response.Response;
import com.damian.gs.service.custom.GuideService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:8080")
public class GuideController {
    @Autowired
    private GuideService guideService;
    @PostMapping(path = "/saveGuide",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> saveGuide(@RequestBody GuideDTO guideDTO){
        return guideService.add(guideDTO);
    }
    @PutMapping(path = "/updateGuide",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> updateGuide(@RequestBody GuideDTO guideDTO){
        return guideService.update(guideDTO);
    }
    @GetMapping(path = "/getGuide",params = "guideID",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getGuide(@RequestParam("guideID")String guideID){
        return guideService.search(guideID);
    }
    @DeleteMapping(path = "/deleteGuide",params = "guideID",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteGuide(@RequestParam("guideID")String guideID){
        return guideService.delete(guideID);
    }
    @GetMapping(path = "/getAllGuides",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getAllGuides(){
        return guideService.getAll();
    }



}
