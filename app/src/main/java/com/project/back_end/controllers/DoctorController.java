package com.project.back_end.controllers;

import com.project.back_end.models.Doctor;
import com.project.back_end.DTO.Login;
import com.project.back_end.services.DoctorService;
import com.project.back_end.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("${api.path:}" + "doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private Service service;

    @GetMapping("/availability/{user}/{doctorId}/{date}/{token}")
    public ResponseEntity<?> getDoctorAvailability(
            @PathVariable("user") String user,
            @PathVariable("doctorId") Long doctorId,
            @PathVariable("date") String date,
            @PathVariable("token") String token) {
        
        ResponseEntity<Map<String, String>> tokenCheck = service.validateToken(token, user);
        if (tokenCheck != null) return tokenCheck;

        java.time.LocalDate targetDate = java.time.LocalDate.parse(date);
        List<String> availableSlots = doctorService.getDoctorAvailability(doctorId, targetDate);
        
        Map<String, Object> response = new HashMap<>();
        response.put("availability", availableSlots);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getListofDoctors() {
        return ResponseEntity.ok(doctorService.getDoctors());
    }

    @PostMapping("/{token}")
    public ResponseEntity<Map<String, String>> addNewDoctor(
            @PathVariable("token") String token,
            @RequestBody Doctor doctor) {
        
        ResponseEntity<Map<String, String>> tokenCheck = service.validateToken(token, "admin");
        if (tokenCheck != null) return tokenCheck;

        int status = doctorService.saveDoctor(doctor);
        Map<String, String> response = new HashMap<>();

        if (status == 1) {
            response.put("message", "Doctor added to db");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else if (status == -1) {
            response.put("error", "Doctor already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } else {
            response.put("error", "Some internal error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> doctorLogin(@RequestBody Login login) {
        return doctorService.validateDoctor(login);
    }

    @PutMapping("/{token}")
    public ResponseEntity<Map<String, String>> updateDoctorDetails(
            @PathVariable("token") String token,
            @RequestBody Doctor doctor) {
        
        ResponseEntity<Map<String, String>> tokenCheck = service.validateToken(token, "admin");
        if (tokenCheck != null) return tokenCheck;

        int status = doctorService.updateDoctor(doctor);
        Map<String, String> response = new HashMap<>();

        if (status == 1) {
            response.put("message", "Doctor updated");
            return ResponseEntity.ok(response);
        } else if (status == -1) {
            response.put("error", "Doctor not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            response.put("error", "Some internal error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}/{token}")
    public ResponseEntity<Map<String, String>> deleteDoctor(
            @PathVariable("id") long id,
            @PathVariable("token") String token) {
        
        ResponseEntity<Map<String, String>> tokenCheck = service.validateToken(token, "admin");
        if (tokenCheck != null) return tokenCheck;

        int status = doctorService.deleteDoctor(id);
        Map<String, String> response = new HashMap<>();

        if (status == 1) {
            response.put("message", "Doctor deleted successfully");
            return ResponseEntity.ok(response);
        } else if (status == -1) {
            response.put("error", "Doctor not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            response.put("error", "Some internal error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
