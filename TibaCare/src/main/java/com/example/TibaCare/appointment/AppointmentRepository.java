package com.example.TibaCare.appointment;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    @Query("SELECT a FROM appointment a WHERE a.email = ?1")
    Optional<Appointment> findAppointmentByEmail(String email);

    @Query("SELECT a FROM appointment a WHERE a.national_identity_card = ?1")
    Optional<Appointment> findAppointmentBynational_identity_card(String national_identity_card);
}
