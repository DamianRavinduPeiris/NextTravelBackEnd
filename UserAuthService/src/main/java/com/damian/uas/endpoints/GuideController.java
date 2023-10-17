package com.damian.uas.endpoints;

import com.damian.uas.dto.GuideDTO;
import com.damian.uas.interfaces.GuideInterface;
import com.damian.uas.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/guide")
@CrossOrigin
public class GuideController {
    @Autowired
    private GuideInterface guideInterface;
    @PostMapping(path = "/saveGuide",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> saveGuide(@RequestBody GuideDTO guideDTO){
        return guideInterface.saveGuide(guideDTO);
    }
    @PutMapping(path = "/updateGuide",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> updateGuide(@RequestBody GuideDTO guideDTO){
        return guideInterface.updateGuide(guideDTO);
    }
    @GetMapping(path = "/getGuide",params = "guideID",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getGuide(@RequestParam("guideID")String guideID){
        return guideInterface.getGuide(guideID);
    }
    @DeleteMapping(path = "/deleteGuide",params = "guideID",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteGuide(@RequestParam("guideID")String guideID){
        return guideInterface.deleteGuide(guideID);
    }
    @GetMapping(path = "/getAllGuides",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getAllGuides(){
        return guideInterface.getAllGuides();
    }

}
