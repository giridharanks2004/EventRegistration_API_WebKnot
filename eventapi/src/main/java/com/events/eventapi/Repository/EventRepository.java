package com.events.eventapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.eventapi.Model.Event;

public interface EventRepository extends JpaRepository<Event,Long> {
    
}
