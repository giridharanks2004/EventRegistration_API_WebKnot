package com.events.eventapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAll(){
        return new ResponseEntity<>(eventService.allEvents(),HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<List<Event>> getAllevents(@RequestParam("cid") Long id){
        return new ResponseEntity<>(eventService.getallEventsbbyid(id),HttpStatus.OK);
    }
    @PutMapping("/admin/update")
    public ResponseEntity<Event> updateEvent(@RequestParam("EventID") long eid, @RequestBody Event e){
        ResponseEntity<Event> res = new ResponseEntity<>(eventService.update(eid, e),HttpStatus.OK);
        if(res.getBody() == null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return res;
    }

    @DeleteMapping("/admin/delete")
    public ResponseEntity<String> deleteEvent(@RequestParam("eventID") long id){
        try{
            eventService.deleteEvent(id);
            return new ResponseEntity<>("deletion done",HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("deletion failed",HttpStatus.NOT_FOUND);
        }
    }
}
