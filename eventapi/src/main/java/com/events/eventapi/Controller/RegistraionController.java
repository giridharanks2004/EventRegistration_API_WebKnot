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

import com.events.eventapi.Model.Event;
import com.events.eventapi.Model.Registration;
import com.events.eventapi.Model.Student;
import com.events.eventapi.Repository.EventRepository;
import com.events.eventapi.Repository.StudentRepository;
import com.events.eventapi.Service.RegistrationService;

@RestController
@RequestMapping("api/colleges/event/regis")

public class RegistraionController {
    @Autowired
    private EventRepository EventDB;
    @Autowired
    private StudentRepository StudentDB;
    @Autowired
    private RegistrationService RegService;

    @PostMapping("/register")
    public ResponseEntity<?> createRegis(@RequestBody Registration reg){
         if (reg.getStudent().getId() == null) {
            return ResponseEntity.badRequest().body("Student id must not be null");
        }
        if (reg.getEvent().getEventid() == null) {
            return ResponseEntity.badRequest().body("Event id must not be null");
        }
        Student student = StudentDB.findById(reg.getStudent().getId()).orElse(null);
        Event event = EventDB.findById(reg.getEvent().getEventid()).orElse(null);
        if(event == null || student == null){
            return new ResponseEntity<>("not found check with the student id or event id",HttpStatus.NOT_FOUND);
        }
        reg.setStudent(student);
        reg.setEvent(event);
        return new ResponseEntity<>(RegService.createRegis(reg),HttpStatus.OK);
    }
    @PutMapping("/cancel")
    public ResponseEntity<?> CancelRegistration(@RequestParam("regID") long id){
        Registration reg = RegService.getByRegid(id);
        if(reg == null){
            return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
        }
        reg.setStatus("cancelled");
        return new ResponseEntity<>(RegService.updateStatus(reg),HttpStatus.OK);
    }
    @GetMapping("/student")
    public ResponseEntity<List<Registration>> getStudentRegis(@RequestParam("studentID") long id){
        return new ResponseEntity<>(RegService.allRegis(id),HttpStatus.OK);
    }
}
