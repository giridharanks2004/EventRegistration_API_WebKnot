package com.events.eventapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.eventapi.Model.Registration;

public interface RegistrationRepository extends JpaRepository<Registration,Long>{
    
}
