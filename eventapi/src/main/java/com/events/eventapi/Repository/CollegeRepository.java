package com.events.eventapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.events.eventapi.Model.College;

@Repository
public interface CollegeRepository extends JpaRepository<College,Long> {
    
}
