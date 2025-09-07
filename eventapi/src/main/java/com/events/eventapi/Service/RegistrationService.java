package com.events.eventapi.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.events.eventapi.Model.Registration;
import com.events.eventapi.Repository.RegistrationRepository;

@Service
public class RegistrationService {
    @Autowired
    RegistrationRepository RegisDB;

    public Registration createRegis(Registration reg){
        return RegisDB.save(reg);
    }

    public Registration getByRegid(long id){
        return RegisDB.findById(id).orElse(null);
    }

    public Registration updateStatus(Registration reg){
        return RegisDB.save(reg);
    }

    public List<Registration> allRegis(long id){
        return RegisDB.findByStudentId(id);
    }
}
