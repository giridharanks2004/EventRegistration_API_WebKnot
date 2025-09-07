package com.events.eventapi.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.events.eventapi.Model.College;
import com.events.eventapi.Repository.CollegeRepository;

@Service
public class CollegeService {

    @Autowired
    CollegeRepository collegeDB;

    public College addCollege(College college){
        return collegeDB.save(college);
    }

    public List<College> getColleges(){
        return collegeDB.findAll();
    }

    public College getbyID(Long id){
        return collegeDB.findById(id).orElse(null);
    }

    public void deletecollege(long id) throws Exception{
        if(getbyID(id)==null){
            throw new Exception("not found");
        }
        collegeDB.delete(getbyID(id));
    }
    
}
