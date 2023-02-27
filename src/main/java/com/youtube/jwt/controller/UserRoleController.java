package com.youtube.jwt.controller;

import com.youtube.jwt.dao.UserDao;
import com.youtube.jwt.entity.User;
import com.youtube.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasRole('User')")
@CrossOrigin
public class UserRoleController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @GetMapping("forUser/profile/{userName}")
    public ResponseEntity<User> userDetails(@PathVariable  String userName) throws Exception {
        User user = this.userDao.findById(userName).orElseThrow(() -> new Exception("Resource not found = Raj"));
        return ResponseEntity.ok(user);
    }

    @GetMapping("forUser/home/{userName}")
    public ResponseEntity<User> home(@PathVariable String userName) throws Exception {
        User user = this.userDao.findById(userName).orElseThrow(() -> new Exception("Resource not found = Raj"));
        System.out.println("Home " + userName);
        return ResponseEntity.ok(user);
    }
}
