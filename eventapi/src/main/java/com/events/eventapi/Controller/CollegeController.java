package com.events.eventapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.events.eventapi.Model.College;
import com.events.eventapi.Service.CollegeService;

@RestController
@RequestMapping("/api/colleges")
public class CollegeController {

    @Autowired
    CollegeService collegeService;

    @PostMapping("/add")
    public ResponseEntity<?> addCollege(@RequestBody College c){
        ResponseEntity<College> res = new ResponseEntity<>(collegeService.addCollege(c),HttpStatus.CREATED);
        
        if(!res.hasBody()){
            return new ResponseEntity<>("college details not added try again",HttpStatus.CONFLICT);
        }

        return res;
    }

    @GetMapping("/all")
    public ResponseEntity<List<College>> getall(){
        return new ResponseEntity<>(collegeService.getColleges(),HttpStatus.OK);
    }
}
