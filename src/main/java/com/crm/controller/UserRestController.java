package com.crm.controller;

import com.crm.entity.User;
import com.crm.exception.UserNotFoundException;
import com.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        user.setId(0);
        userService.saveUser(user);
        return user;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user){
        userService.saveUser(user);
        return user;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable int userId){

        User user = userService.getUser(userId);

        if(user == null){
            throw new UserNotFoundException("User by Id '" + userId + "' not found.");
        }

        userService.deleteUser(userId);
        return "Deleted user id - " + userId;
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable int userId){

        User user = userService.getUser(userId);

        if(user == null){
            throw new UserNotFoundException("User by Id '" + userId + "' not found.");
        }
        return user;
    }
}
