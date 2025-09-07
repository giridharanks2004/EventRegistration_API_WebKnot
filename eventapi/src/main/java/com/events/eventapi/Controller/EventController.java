package com.events.eventapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.events.eventapi.Model.College;
import com.events.eventapi.Model.Event;
import com.events.eventapi.Repository.CollegeRepository;
import com.events.eventapi.Service.EventService;

@RestController
@RequestMapping("api/colleges/event")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private CollegeRepository collegeDB;
    @PostMapping("/admin/create")
    public ResponseEntity<?> createEvent(@RequestBody Event event){
        College college = collegeDB.findById(event.getCollege().getId()).orElse(null);
        if(college == null){
            return new ResponseEntity<>("no college found!",HttpStatus.NOT_FOUND);
        }
        event.setCollege(college);
        return new ResponseEntity<>(eventService.createEvent(event),HttpStatus.CREATED);
    }
}
