# 🏥 Clinic Management System - User Stories Documentation

## 1. Admin User Stories
### 📋 Story 1: System Access & Provisioning
*   **User Story:** As an **Admin**, I want to register and manage medical practitioner accounts so that only authorized healthcare personnel can access clinic schedules.
*   **Agile Metrics:**
    *   **Priority:** 🔥 High
    *   **Story Points:** 8
*   **Acceptance Criteria:**
    *   System must encrypt passwords before saving them.
    *   Admin can create, update, or deactivate doctor profiles.

---

## 2. Patient User Stories
### 📋 Story 2: Appointment Scheduling
*   **User Story:** As a **Patient**, I want to look up available doctor time slots and book an appointment online so that I can schedule a check-up without calling the front desk.
*   **Agile Metrics:**
    *   **Priority:** 🔥 High
    *   **Story Points:** 8
*   **Acceptance Criteria:**
    *   Patients can view open slots by doctor or specialization.
    *   Double-booking identical time slots is blocked by the system.

---

## 3. Doctor User Stories
### 📋 Story 3: Availability & Patient Tracking
*   **User Story:** As a **Doctor**, I want to set my daily availability times and view my upcoming patient appointments so that I can prepare for my day's clinical visits.
*   **Agile Metrics:**
    *   **Priority:** 🔥 High
    *   **Story Points:** 5
*   **Acceptance Criteria:**
    *   Doctors can log into a dedicated dashboard view.
    *   Doctors can view a chronologically ordered list of scheduled check-ups.

---

## 4. Nurse User Stories
### 📋 Story 4: Record Patient Vitals
*   **User Story:** As a **Nurse**, I want to log a patient's vitals (blood pressure, heart rate, temperature, and weight) before their doctor consultation so that the treating physician has up-to-date physiological data immediately available.
*   **Agile Metrics:**
    *   **Priority:** 🔥 High
    *   **Story Points:** 3
*   **Acceptance Criteria:**
    *   The nurse must be able to search for the patient by name or appointment ID.
    *   The system must validate that data fields (like blood pressure syntax) are well-formed before saving.
    *   The recorded vitals must instantly bind to the current active appointment timeline.

### 📋 Story 5: Manage Triaging and Queues
*   **User Story:** As a **Nurse**, I want to update a patient's check-in status and assign a triage priority level (e.g., Routine, Urgent) so that the clinic queue updates in real-time and doctors know who to see next.
*   **Agile Metrics:**
    *   **Priority:** ⚡ Medium
    *   **Story Points:** 5
*   **Acceptance Criteria:**
    *   The dashboard must display a live list of patients currently waiting in the clinic lobby.
    *   Updating a patient's priority must automatically re-sort the doctor's queue display.

---

## 5. Receptionist User Stories
### 📋 Story 6: Walk-in Check-in and Registration
*   **User Story:** As a **Receptionist**, I want to quickly register a new walk-in patient or check in an existing one with an appointment so that their arrival is officially stamped in the system queue.
*   **Agile Metrics:**
    *   **Priority:** 🔥 High
    *   **Story Points:** 5
*   **Acceptance Criteria:**
    *   The interface must permit searching for patients by phone number or national health ID.
    *   Checking in a patient must transition their status tracker flag from `0` (Upcoming) to `1` (Arrived).

### 📋 Story 7: Reschedule or Cancel Appointments
*   **User Story:** As a **Receptionist**, I want to modify or cancel an appointment on behalf of a patient who calls the front desk so that the doctor's schedule remains accurate and available times open up for others.
*   **Agile Metrics:**
    *   **Priority:** ⚡ Medium
    *   **Story Points:** 5
*   **Acceptance Criteria:**
    *   Canceling an appointment must free up the doctor's time slot immediately in the system.
    *   Rescheduling must leverage the central database validator logic to prevent double-booking.
