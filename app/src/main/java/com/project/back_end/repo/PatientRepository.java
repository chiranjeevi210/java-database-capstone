package com.project.back_end.repo;

import com.project.back_end.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Patient entity database transactions.
 * Extends JpaRepository to leverage standard Spring Data JPA CRUD capabilities.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    /**
     * Locates a unique patient record matching an exact email address index.
     * <p>
     * Spring Data JPA automatically translates this method name into the following query:
     * {@code SELECT p FROM Patient p WHERE p.email = :email}
     * </p>
     * 
     * @param email The unique email address string associated with the target patient account.
     * @return The matched Patient entity instance, or null if no database record exists.
     * @example Patient user = patientRepository.findByEmail("jane.doe@example.com");
     */
    Patient findByEmail(String email);

    /**
     * Locates a patient record matching either the provided email address OR phone number.
     * <p>
     * This query is utilized as a safety guardrail during registration workflows to ensure 
     * that duplicate profile records are not injected into the system. It translates to:
     * {@code SELECT p FROM Patient p WHERE p.email = :email OR p.phone = :phone}
     * </p>
     * 
     * @param email The target email address string to evaluate for existing profile matches.
     * @param phone The target telephone number string to evaluate for existing profile matches.
     * @return The matched Patient entity if either condition hits, or null if the credentials are safe to register.
     * @example Patient conflict = patientRepository.findByEmailOrPhone("user@test.com", "888-111-1111");
     */
    Patient findByEmailOrPhone(String email, String phone);
}
