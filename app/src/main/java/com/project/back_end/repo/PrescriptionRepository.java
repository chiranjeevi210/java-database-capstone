package com.project.back_end.repo;

import com.project.back_end.models.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Encapsulates clinical prescription history documents residing inside a MongoDB collection layer.
 * Extends MongoRepository managing String document keys.
 */
@Repository
public interface PrescriptionRepository extends MongoRepository<Prescription, String> {

    /**
     * Finds list arrays of all prescriptions linked to a specified relational appointment identity.
     * 
     * @param appointmentId Relational unique database ID marker tracking back to the core transaction.
     * @return List array containing mapped historical clinic script entries.
     */
    List<Prescription> findByAppointmentId(Long appointmentId);
}
