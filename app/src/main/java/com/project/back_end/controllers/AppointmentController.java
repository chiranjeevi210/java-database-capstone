package com.project.back_end.controllers;

import com.project.back_end.models.Appointment;
import com.project.back_end.services.AppointmentService;
import com.project.back_end.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private Service service;

    @GetMapping("/{date}/{patientName}/{token}")
    public ResponseEntity<?> getAppointments(
            @PathVariable("date") String date,
            @PathVariable("patientName") String patientName,
            @PathVariable("token") String token) {
        
        // Secure validation gate: restrict logs to verified medical doctors
        ResponseEntity<Map<String, String>> tokenCheck = service.validateToken(token, "doctor");
        if (tokenCheck != null) {
            return tokenCheck;
        }
        
        java.time.LocalDate targetDate = java.time.LocalDate.parse(date);
        return ResponseEntity.ok(appointmentService.getAppointment(patientName, targetDate, token));
    }

    @PostMapping("/{token}")
    public ResponseEntity<Map<String, String>> bookAppointment(
            @PathVariable("token") String token,
            @RequestBody Appointment appointment) {
        
        ResponseEntity<Map<String, String>> tokenCheck = service.validateToken(token, "patient");
        if (tokenCheck != null) return tokenCheck;

        int validationCheck = service.validateAppointment(appointment);
        Map<String, String> response = new HashMap<>();

        if (validationCheck == 1) {
            int success = appointmentService.bookAppointment(appointment);
            if (success == 1) {
                response.put("message", "Appointment booked successfully");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
        } else if (validationCheck == 0) {
            response.put("error", "Requested timeslot is unavailable");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } else if (validationCheck == -1) {
            response.put("error", "Doctor profile not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.put("error", "Internal booking processing error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @PutMapping("/{token}")
    public ResponseEntity<Map<String, String>> updateAppointment(
            @PathVariable("token") String token,
            @RequestBody Appointment appointment) {
        
        ResponseEntity<Map<String, String>> tokenCheck = service.validateToken(token, "patient");
        if (tokenCheck != null) return tokenCheck;

        return appointmentService.updateAppointment(appointment);
    }

    @DeleteMapping("/{id}/{token}")
    public ResponseEntity<Map<String, String>> cancelAppointment(
            @PathVariable("id") long id,
            @PathVariable("token") String token) {
        
        ResponseEntity<Map<String, String>> tokenCheck = service.validateToken(token, "patient");
        if (tokenCheck != null) return tokenCheck;

        return appointmentService.cancelAppointment(id, token);
    }
}
