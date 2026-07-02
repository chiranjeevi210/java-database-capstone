# Patient Appointment Portal - Agile User Stories

This document outlines the core functional requirements for the Smart Clinic Management System using the standard agile user story format: *As a... I want to... So that...*

---

## 1. Admin User Stories
### Story 1: System Access & Provisioning
*   **User Story:** As an **Admin**, I want to register and manage medical practitioner accounts so that only authorized healthcare personnel can access clinic schedules.
*   **Acceptance Criteria:**
    *   System must encrypt passwords before saving them.
    *   Admin can create, update, or deactivate doctor profiles.

---

## 2. Patient User Stories
### Story 2: Appointment Scheduling
*   **User Story:** As a **Patient**, I want to look up available doctor time slots and book an appointment online so that I can schedule a check-up without calling the front desk.
*   **Acceptance Criteria:**
    *   Patients can view open slots by doctor or specialization.
    *   Double-booking identical time slots is blocked by the system.

---

## 3. Doctor User Stories
### Story 3: Availability & Patient Tracking
*   **User Story:** As a **Doctor**, I want to set my daily availability times and view my upcoming patient appointments so that I can prepare for my day's clinical visits.
*   **Acceptance Criteria:**
    *   Doctors can log into a dedicated dashboard view.
    *   Doctors can view a chronologically ordered list of scheduled check-ups.
