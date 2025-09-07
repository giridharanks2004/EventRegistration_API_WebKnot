package com.events.eventapi.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long eventid;
    @NotBlank(message = "event name should be entered")
    String eventname;
    @NotBlank(message = "event type should be selected")
    String eventype;
    @ManyToOne
    @JoinColumn(name = "collegeID")
    private College college;
}
