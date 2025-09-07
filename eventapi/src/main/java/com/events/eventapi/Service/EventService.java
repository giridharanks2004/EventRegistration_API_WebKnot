package com.events.eventapi.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.events.eventapi.Model.Event;
import com.events.eventapi.Repository.EventRepository;

@Service
public class EventService {

    @Autowired
    EventRepository EventDB;

    public Event createEvent(Event e){
        return EventDB.save(e);
    }

    public List<Event> allEvents(){
        return EventDB.findAll();
    }

    public List<Event> getallEventsbbyid(long id){
        return EventDB.findByCollegeId(id);
    }
}
