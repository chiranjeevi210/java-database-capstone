package com.project.back_end.services;

import com.project.back_end.repo.AdminRepository;
import com.project.back_end.repo.DoctorRepository;
import com.project.back_end.repo.PatientRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date:
@Component
public class TokenService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    // The configured secret field injected from the application.properties file
    @Value("${jwt.secret:defaultSecretKeyForClinicManagementSystem2026SecureString}")
    private String jwtSecret;

    /**
     * Retrieves and generates the secret cryptographic signing key.
     * <p>
     * <b>Utilization of the Configured Secret Field:</b>
     * This method directly reads the configured string field {@code jwtSecret}. 
     * First, it converts the raw characters of the string into a byte array using 
     * UTF-8 standard encoding (via {@code StandardCharsets.UTF_8}). Next, this raw byte array 
     * is passed into {@code Keys.hmacShaKeyFor()}, which processes the bytes to securely 
     * instantiate a cryptographic {@code SecretKey} suitable for verifying and signing 
     * tokens with the HMAC-SHA encryption algorithm.
     * </p>
     * 
     * @return A secure SecretKey instance derived from the configured jwtSecret string.
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = this.jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Generates a token using secure parsing algorithms and the dedicated signing key helper.
     */
    public String generateToken(String identifier) {
        long expirationTimeMs = 7 * 24 * 60 * 60 * 1000L; // 7 days
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTimeMs);

        return Jwts.builder()
                .setSubject(identifier)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Decodes and extracts the user identifier string payload using the modern builder pattern and signing key helper.
     */
    public String extractIdentifier(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Verifies token authenticity against database contexts
     */
    public boolean validateToken(String token, String user) {
        String identifier = extractIdentifier(token);
        if (identifier == null) {
            return false;
        }

        try {
            if ("admin".equalsIgnoreCase(user)) {
                return adminRepository.findByUsername(identifier) != null;
            } else if ("doctor".equalsIgnoreCase(user)) {
                return doctorRepository.findByEmail(identifier) != null;
            } else if ("patient".equalsIgnoreCase(user) || "loggedPatient".equalsIgnoreCase(user)) {
                return patientRepository.findByEmail(identifier) != null;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}