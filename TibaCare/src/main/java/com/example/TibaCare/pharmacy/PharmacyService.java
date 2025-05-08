package com.example.TibaCare.pharmacy;

import com.example.TibaCare.user.Users;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class PharmacyService {
    @Autowired
    private  PharmacyRepository pharmacyRepository;

    public List<Pharmacy> getpharmacy(){
        return pharmacyRepository.findAll();
    }

    public void addNewPharmacy(Pharmacy pharmacy) {
        pharmacyRepository.save(pharmacy);
    }

    public void deletepharmacy(int pharmacyid ) {
        boolean exists =  pharmacyRepository.existsById(pharmacyid);
        if (!exists){
            throw new IllegalStateException("pharmacy with id " + pharmacyid + "does not exists");
        }
        pharmacyRepository.deleteById(pharmacyid);
    }
    @Transactional
    public void updatepharmacy(int pharmacyId , String name , String email) {
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId)
                .orElseThrow(() -> new IllegalStateException("pharmacy with id " + pharmacyId +
                        "does not exists"));
        if (name != null && name.length() > 0 && !Objects.equals(pharmacy.getMedicinename(), name)) {
            pharmacy.setMedicinename(name);
        }
    }

}
