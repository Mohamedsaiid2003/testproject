package com.example.TibaCare.pharmacy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {

    boolean existsById(Long pharmacyid);
}
