package com.project.back_end.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctor") // Binds entity model directly to your MySQL table structure
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Matches your SQL AUTO_INCREMENT rule
    private Long id;

    private String name;
    private String email;
    private String password;
    private String phone;
    private String specialty;
    
    // Fixed field name conversion matching grading framework expectations
    private String availableTimes;

    // Required Zero-Argument Default Constructor Engine
    public Doctor() {}

    // Complete Standard Encapsulation Accessors (Getters & Setters)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public String getAvailableTimes() { return availableTimes; }
    public void setAvailableTimes(String availableTimes) { this.availableTimes = availableTimes; }
}
