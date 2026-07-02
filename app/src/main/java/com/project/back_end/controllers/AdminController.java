package com.project.back_end.controllers;

import com.project.back_end.models.Admin; // Assuming baseline model exists
import com.project.back_end.services.Service; // Central Service class
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("${api.path:}" + "admin")
public class AdminController {

    @Autowired
    private Service service;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> adminLogin(@RequestBody Admin receivedAdmin) {
        // Delegates credential checking and token mapping to the backend service
        return service.validateAdmin(receivedAdmin);
    }
}
