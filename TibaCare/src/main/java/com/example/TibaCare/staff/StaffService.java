package com.example.TibaCare.staff;

import com.example.TibaCare.appointment.Appointment;
import com.example.TibaCare.department.Department;
import com.example.TibaCare.department.DepartmentRepository;
import com.example.TibaCare.enums.Role;
import com.example.TibaCare.security.JwtResponse;
import com.example.TibaCare.user.Users;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.TibaCare.security.JWTService;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
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

    @PostConstruct
    public void staticAdmin() {
        String adminEmail = "admin@tibacare.com";
        String nationalId = "30309122103914";

        if (staffRepository.findByEmail(adminEmail).isEmpty()) {
            Staff admin = new Staff();
            admin.setFirstname("Mohamed");
            admin.setLastname("Saeed");
            admin.setGender("Male");
            admin.setRole(Role.ADMIN);
            admin.setMobilnumber("01557646848");
            admin.setEmail(adminEmail);
            admin.setAdress("Giza");
            admin.setNational_identity_card(nationalId);
            admin.setDate_of_birth(LocalDate.of(2003, 9, 12));
            admin.setPassword(encoder.encode("Mohamed@2003")); // كلمة سر مؤقتة

            admin.setDepartment(null); // لأنه مش تابع لقسم معين
            staffRepository.save(admin);
            System.out.println("Static admin created.");
        }
    }


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
    public JwtResponse verify(StaffLoginRequest request) throws AccessDeniedException {
        Optional<Staff> foundStaff = staffRepository.findByEmail(request.getEmail());

        if (foundStaff.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        Staff staff = foundStaff.get();

        if (!staff.getRole().equals(request.getRole())) {
            throw new AccessDeniedException("Unauthorized role");
        }

        Authentication authentication = authMAnager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(staff.getEmail());
            return new JwtResponse(token);
        }

        throw new BadCredentialsException("Invalid credentials");
    }



}
