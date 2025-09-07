package com.events.eventapi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.eventapi.Model.Registration;

public interface RegistrationRepository extends JpaRepository<Registration,Long>{
    List<Registration> findByStudentId(Long id);
    List<Registration> findByEventEventid(Long eventid);
    long countByEventEventid(Long eventid);
    long countByEventEventidAndAttendance(Long eventid, String attendance);
    List<Registration> findByEventEventidAndAttendance(Long eventid, String attendance);
}
