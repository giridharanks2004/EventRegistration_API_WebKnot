package com.events.eventapi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.events.eventapi.Repository.RegistrationRepository;

@Service
public class RegistrationService {
    @Autowired
    RegistrationRepository RegisDB;

    
}
