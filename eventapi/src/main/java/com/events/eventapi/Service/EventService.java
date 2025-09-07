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

    public Event getEventByid(long id){
        return EventDB.findById(id).orElse(null);
    }
    public Event update(long id,Event e){
        if(getEventByid(id)==null){
            return null;
        }
        e.setEventid(id);
        return EventDB.save(e);
    }

    public void deleteEvent(Long id) throws Exception{
        Event e = getEventByid(id);
        if(e == null){
            throw new Exception("no event registered recheck the event id please");
        }
        EventDB.delete(e);
    }

}
