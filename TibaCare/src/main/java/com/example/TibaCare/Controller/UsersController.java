package com.example.TibaCare.Controller;

import com.example.TibaCare.security.JwtResponse;
import com.example.TibaCare.staff.Staff;
import com.example.TibaCare.staff.StaffLoginRequest;
import com.example.TibaCare.staff.StaffService;
import com.example.TibaCare.user.Users;
import com.example.TibaCare.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UsersController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private UsersService usersService;

    @GetMapping("/getuserdata")
    public List<Users> getData() {
        return usersService.getuser();
    }

    @PostMapping(path = "/SignUp")
    public void registerNewUser(@RequestBody Users users) {
        usersService.addNewUser(users);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        usersService.deleteuser(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(
            @PathVariable("userId") Long userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ){
        usersService.updateUser(userId,name,email);

    }
    @PostMapping("/SignIn/user")
    public String verifyuser(@RequestBody Users users) {
        return usersService.verify(users);
    }
    @PostMapping("/SignIn/staff")
    public ResponseEntity<?> verifystaff(@RequestBody StaffLoginRequest request) {
        try {
            JwtResponse response = staffService.verify(request);
            return ResponseEntity.ok(response);
        } catch (UsernameNotFoundException | BadCredentialsException | AccessDeniedException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
    }


}
