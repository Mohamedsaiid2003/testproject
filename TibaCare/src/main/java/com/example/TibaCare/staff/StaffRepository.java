package com.example.TibaCare.staff;


import com.example.TibaCare.appointment.Appointment;
import com.example.TibaCare.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {

    @Query("SELECT s FROM Staff s WHERE s.email = ?1")
    Optional<Staff> findByEmail(String email);

    @Query("SELECT s FROM Staff s WHERE s.national_identity_card = ?1")
    Optional<Staff> findBynational_identity_card(String national_identity_card);

    @Query("SELECT u FROM Staff u WHERE u.email = ?1")
    Users findByUsername(String email);
}
