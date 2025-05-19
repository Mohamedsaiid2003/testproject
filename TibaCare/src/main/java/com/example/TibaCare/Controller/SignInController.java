package com.example.TibaCare.Controller;

import com.example.TibaCare.staff.Staff;
import com.example.TibaCare.staff.StaffService;
import com.example.TibaCare.user.Users;
import com.example.TibaCare.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/signin")
public class SignInController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private  UsersService usersService;

    @PostMapping("/SignIn/user")
    public String verifyuser(@RequestBody Users users) {
        return usersService.verify(users);
    }
    @PostMapping("/SignIn/staff")
    public String verifystaff(@RequestBody Staff staff) {
        return staffService.verifystaff(staff);
    }

}
