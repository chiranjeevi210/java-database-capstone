package com.project.back_end.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/admin/dashboard")
    public String showAdminDashboard() {
        return "admin/adminDashboard";
    }

    @GetMapping("/doctor/dashboard")
    public String showDoctorDashboard() {
        return "doctor/doctorDashboard";
    }
}
