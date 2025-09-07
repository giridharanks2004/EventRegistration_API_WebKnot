package com.events.eventapi.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.events.eventapi.Model.Registration;
import com.events.eventapi.Repository.RegistrationRepository;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    @Autowired
    private RegistrationRepository registrationRepo;
    @PutMapping("/mark/{regId}/")
    public ResponseEntity<?> markAttendance(@PathVariable Long regId,
                                            @RequestParam String status) {
        Registration reg = registrationRepo.findById(regId).orElse(null);
        if (reg == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registration not found");
        }

        if (!status.equalsIgnoreCase("present") && !status.equalsIgnoreCase("absent")) {
            return ResponseEntity.badRequest().body("Status must be 'present' or 'absent'");
        }

        reg.setAttendance(status.toLowerCase());
        registrationRepo.save(reg);

        return ResponseEntity.ok("Attendance marked as " + status);
    }

    @GetMapping("/summary/{eventId}")
    public ResponseEntity<?> getEventSummary(@PathVariable Long eventId) {
        long total = registrationRepo.countByEventEventid(eventId);
        long present = registrationRepo.countByEventEventidAndAttendance(eventId, "present");
        long absent = registrationRepo.countByEventEventidAndAttendance(eventId, "absent");

        Map<String, Object> summary = new HashMap<>();
        summary.put("total_registered", total);
        summary.put("present_count", present);
        summary.put("absent_count", absent);

        return ResponseEntity.ok(summary);
    }

    @GetMapping("/present/{eventId}")
    public ResponseEntity<?> getPresentList(@PathVariable Long eventId) {
        return ResponseEntity.ok(registrationRepo.findByEventEventidAndAttendance(eventId, "present"));
    }

    @GetMapping("/absent/{eventId}")
    public ResponseEntity<?> getAbsentList(@PathVariable Long eventId) {
        return ResponseEntity.ok(registrationRepo.findByEventEventidAndAttendance(eventId, "absent"));
    }
}

