package com.example.TibaCare.staff;

import com.example.TibaCare.appointment.Appointment;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }
    public List<Staff> getstaff(){
        return staffRepository.findAll();
    }

    public void addNewStaff(Staff staff) {
        Optional<Staff> optionalStaff = staffRepository.findByEmail(staff.getEmail());
        if(optionalStaff.isPresent()){
            throw new IllegalStateException("email taken");
        }
        Optional<Staff> staffOptional = staffRepository.findBynational_identity_card(staff.getNational_identity_card());
        if (staffOptional.isPresent()){
            throw new IllegalStateException("national_identity_card taken");
        }
        staffRepository.save(staff);
    }

    public void deletestaff(Long staffId) {
        boolean exists =  staffRepository.existsById(staffId);
        if (!exists){
            throw new IllegalStateException("staff with id " + staffId + "does not exists");
        }
        staffRepository.deleteById(staffId);
    }
    @Transactional
    public void updateStaff(Long staffId , String name , String email){
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new IllegalStateException("staff with id " + staffId +
                        "does not exists"));
        if (name != null && name.length() > 0 && !Objects.equals(staff.getName(),name)){
            staff.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(staff.getEmail(),email)){
            Optional<Staff> appointmentOptional = staffRepository.findByEmail((email));
            if (appointmentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            staff.setEmail(email);
        }
    }
}
