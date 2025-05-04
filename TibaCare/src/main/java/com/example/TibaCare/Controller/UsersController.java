package com.example.TibaCare.Controller;

import com.example.TibaCare.user.Users;
import com.example.TibaCare.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UsersController {
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
    @PostMapping("/SignIn")
    public String verify(@RequestBody Users users) {
        return usersService.verify(users);
    }

}
