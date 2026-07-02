package com.project.back_end.DTO;

/**
 * Data Transfer Object (DTO) capturing inbound authentication request bodies.
 * Decoupled completely from relational database entities.
 */
public class Login {

    // Unique login handle (email for Doctor/Patient, username for Admin)
    private String identifier;
    
    // Raw plain-text password from the client login prompt
    private String password;

    /**
     * Default Nullary Constructor
     * Mandatory requirement for structural JSON deserialization by Jackson.
     */
    public Login() {
    }

    /**
     * Parameterized initialization constructor framework.
     */
    public Login(String identifier, String password) {
        this.identifier = identifier;
        this.password = password;
    }

    // Standard POJO Deserialization Getter & Setter Matrix
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
