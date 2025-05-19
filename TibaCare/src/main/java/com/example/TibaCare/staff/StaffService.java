package com.example.TibaCare.staff;

import com.example.TibaCare.appointment.Appointment;
import com.example.TibaCare.department.Department;
import com.example.TibaCare.department.DepartmentRepository;
import com.example.TibaCare.user.Users;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.TibaCare.security.JWTService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StaffService {
    @Autowired
    private  StaffRepository staffRepository;
    @Autowired
    private AuthenticationManager authMAnager;
    @Autowired
    private JWTService jwtService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public List<Staff> getstaff(){
        return staffRepository.findAll();
    }

    public void addNewStaff(Staff staff ) {
        staff.setPassword(encoder.encode(staff.getPassword()));
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
    public String verifystaff(Staff staff) {
        Authentication authentication =
                authMAnager.authenticate(new UsernamePasswordAuthenticationToken(
                        staff.getEmail(), staff.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(staff.getEmail());
        }
        return "fail";
    }
}
