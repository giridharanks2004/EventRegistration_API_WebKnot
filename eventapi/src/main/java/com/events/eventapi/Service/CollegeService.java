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
    
}
