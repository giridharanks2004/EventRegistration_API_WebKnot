package com.events.eventapi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.events.eventapi.Model.Registration;
import com.events.eventapi.Model.Student;
import com.events.eventapi.Repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    StudentRepository StudentDB;

    public Student createStudent(Student s){
        return StudentDB.save(s);    
    }

    public Student getStudentbyID(Long id){
        return StudentDB.findById(id).orElse(null);
    }

    public Student updateInfo(Long id, Student s){
        Student student = getStudentbyID(id);
        if(student == null){
            return null;
        }
        s.setId(id);
        return StudentDB.save(s);
    }

    public void deleteStudent(long id) throws Exception{
        Student student = getStudentbyID(id);
        if(student == null){
            throw new Exception("student not found");
        }
        StudentDB.delete(student);
    }
    
}
