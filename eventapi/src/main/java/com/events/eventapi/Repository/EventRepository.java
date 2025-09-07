package com.events.eventapi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.eventapi.Model.Event;

public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByCollegeId(Long CollegeId);
}
