package com.example.householdBudget.controller;


import com.example.householdBudget.database.entities.UserTableEntity;
import com.example.householdBudget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/getallusers")
    public List<UserTableEntity> getAllUsers() {
        List<UserTableEntity> allUsers = userService.getAllUsers();
        return allUsers;
    }

    @PostMapping("/createuser")
    public UserTableEntity createUser(@RequestBody String name, String userName) {
        return userService.addNewUser(name, userName);
    }

    @DeleteMapping("/users/{id}")
    public UserTableEntity deleteUser(@PathVariable long id) {
        return userService.deleteUserById(id);

    }

}
