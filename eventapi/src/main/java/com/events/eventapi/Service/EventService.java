package com.events.eventapi.Service;

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
}
