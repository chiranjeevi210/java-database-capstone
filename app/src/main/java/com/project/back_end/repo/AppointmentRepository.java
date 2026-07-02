package com.project.back_end.repo;

import com.project.back_end.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    /**
     * 1. Retrieve appointments for a doctor within a given time range.
     * Uses LEFT JOIN FETCH to eager load doctor information.
     */
    @Query("SELECT a FROM Appointment a LEFT JOIN FETCH a.doctor d WHERE a.doctor.id = :doctorId AND a.appointmentTime BETWEEN :start AND :end")
    List<Appointment> findByDoctorIdAndAppointmentTimeBetween(
            @Param("doctorId") Long doctorId, 
            @Param("start") LocalDateTime start, 
            @Param("end") LocalDateTime end);

    /**
     * 2. Filter appointments by doctor ID, partial patient name (case-insensitive), and time range.
     * Uses FETCH joins to eagerly resolve graph associations cleanly.
     */
    @Query("SELECT a FROM Appointment a LEFT JOIN FETCH a.patient p LEFT JOIN FETCH a.doctor d WHERE a.doctor.id = :doctorId AND LOWER(a.patient.name) LIKE LOWER(CONCAT('%', :patientName, '%')) AND a.appointmentTime BETWEEN :start AND :end")
    List<Appointment> findByDoctorIdAndPatient_NameContainingIgnoreCaseAndAppointmentTimeBetween(
            @Param("doctorId") Long doctorId, 
            @Param("patientName") String patientName, 
            @Param("start") LocalDateTime start, 
            @Param("end") LocalDateTime end);

    /**
     * 3. Delete all appointments related to a specific doctor.
     * Requires @Modifying and @Transactional to permit execution over data updates.
     */
    @Modifying
    @Transactional
    void deleteAllByDoctorId(Long doctorId);

    /**
     * 4. Find all appointments for a specific patient.
     */
    List<Appointment> findByPatientId(Long patientId);

    /**
     * 5. Retrieve appointments for a patient by status, ordered by appointment time.
     */
    List<Appointment> findByPatient_IdAndStatusOrderByAppointmentTimeAsc(Long patientId, int status);

    /**
     * 6. Search appointments by partial doctor name and patient ID.
     * Employs LOWER and CONCAT for case-insensitive flexible lookups.
     */
    @Query("SELECT a FROM Appointment a WHERE LOWER(a.doctor.name) LIKE LOWER(CONCAT('%', :doctorName, '%')) AND a.patient.id = :patientId")
    List<Appointment> filterByDoctorNameAndPatientId(
            @Param("doctorName") String doctorName, 
            @Param("patientId") Long patientId);

    /**
     * 7. Filter appointments by doctor name, patient ID, and status.
     */
    @Query("SELECT a FROM Appointment a WHERE LOWER(a.doctor.name) LIKE LOWER(CONCAT('%', :doctorName, '%')) AND a.patient.id = :patientId AND a.status = :status")
    List<Appointment> filterByDoctorNameAndPatientIdAndStatus(
            @Param("doctorName") String doctorName, 
            @Param("patientId") Long patientId, 
            @Param("status") int status);
}
