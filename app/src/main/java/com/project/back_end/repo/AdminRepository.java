package com.project.back_end.repo;

import com.project.back_end.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    /**
     * Custom finder method query built automatically by Spring Data JPA conventions.
     * Evaluates to: SELECT * FROM admin WHERE username = :username
     * 
     * @param username The unique identification login string of the Admin entity.
     * @return The matched Admin model object instance, or null if no record exists.
     */
    Admin findByUsername(String username);
}
