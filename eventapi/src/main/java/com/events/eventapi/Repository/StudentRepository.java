package com.events.eventapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.eventapi.Model.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{
    
}
