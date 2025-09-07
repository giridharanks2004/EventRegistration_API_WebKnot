package com.events.eventapi.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long regid;

    @ManyToOne
    @JoinColumn(name = "eventID")
    Event event;

    @ManyToOne
    @JoinColumn(name = "StudentID")
    Student student;

    @Column(nullable = false)
    String status = "registered";

    @Column(nullable = false)
    String attendance = "absent";


}
