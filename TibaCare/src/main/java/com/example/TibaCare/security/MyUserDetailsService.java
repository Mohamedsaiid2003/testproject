package com.example.TibaCare.security;

import com.example.TibaCare.staff.Staff;
import com.example.TibaCare.staff.StaffRepository;
import  com.example.TibaCare.user.Users;
import com.example.TibaCare.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Staff staff = staffRepository.findByUsername(email);
        if (staff != null) {
            System.out.println("Staff found: " + staff.getUsername());
            return new org.springframework.security.core.userdetails.User(
                    staff.getUsername(),
                    staff.getPassword(),
                    new ArrayList<>() // الصلاحيات
            );
        }

        Users user = userRepository.findByUsername(email);
        if (user != null) {
            System.out.println("User found: " + user.getUsername());
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    new ArrayList<>() // الصلاحيات
            );
        }

        System.out.println("User not found");
        throw new UsernameNotFoundException("User not found");

    }
}
