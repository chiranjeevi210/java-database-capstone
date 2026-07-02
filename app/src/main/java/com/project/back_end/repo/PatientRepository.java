package com.project.back_end.repo;

import com.project.back_end.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    /**
     * 1. Locate a unique patient record matching an email index.
     */
    Patient findByEmail(String email);

    /**
     * 2. Locates an existing file footprint against compound email or phone properties.
     * Essential tool to safeguard workflows from injecting duplicate system registrations.
     */
    Patient findByEmailOrPhone(String email, String phone);
}
