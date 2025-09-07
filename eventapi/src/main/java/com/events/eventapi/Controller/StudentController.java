package com.events.eventapi.Controller;

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
import com.events.eventapi.Model.Student;
import com.events.eventapi.Repository.CollegeRepository;
import com.events.eventapi.Service.StudentService;

@RestController
@RequestMapping("api/colleges/student")
public class StudentController {
    
    @Autowired 
    StudentService studentService;
    @Autowired
    CollegeRepository collegeDB;
    @PostMapping("/create")
    public ResponseEntity<?> createStudent(@RequestBody Student student){
        College college = collegeDB.findById(student.getCollege().getId()).orElse(null);
        if(college == null){
            return new ResponseEntity<>("college id is invalid or not found",HttpStatus.NOT_FOUND);
        }
        student.setCollege(college);
        return new ResponseEntity<>(studentService.createStudent(student),HttpStatus.OK);
    }
    @GetMapping("/info")
    public ResponseEntity<?> GetInfo(@RequestParam("studentID") long id){
        ResponseEntity<Student> res = new ResponseEntity<>(studentService.getStudentbyID(id),HttpStatus.OK);
        if(res.getBody() == null){
            return new ResponseEntity<>("student info not found",HttpStatus.NOT_FOUND);
        }
        return res;

    }
    @PutMapping("/update")
    public ResponseEntity<?> UpdateInfo(@RequestParam("studentID") long id, @RequestBody Student s){
        ResponseEntity<Student> res = new ResponseEntity<>(studentService.updateInfo(id,s),HttpStatus.OK);
        if(res.getBody() == null){
            return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
        }
        return res;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> DeleteStudent(@RequestParam("studentID") long id){
        try{
            studentService.deleteStudent(id);
            return new ResponseEntity<>("deletion done",HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("deltion failed",HttpStatus.NOT_FOUND);
        }
    }
}
