package com.youtube.jwt.controller;

import com.youtube.jwt.dao.UserDao;
import com.youtube.jwt.entity.User;
import com.youtube.jwt.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/forAdmin")
@PreAuthorize("hasRole('Admin')")
public class AdminRoleController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @GetMapping({"/allUsers"})
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> users = this.userService.getAllUser();
        System.out.println("User details sent = "  + users);
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/delete/{userName}")
    public void deleteUser(@PathVariable String userName) {
        this.userService.deleteUser(userName);
    }

    

}
