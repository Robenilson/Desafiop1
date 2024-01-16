package com.roben.demo.controlles;

import com.roben.demo.domain.user.User;
import com.roben.demo.dtos.UserDTO;
import com.roben.demo.serveces.UserServicer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserServicer userServicer;


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user ){
        User newUser=  userServicer.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }








    @GetMapping
    public  ResponseEntity<List<User>>getAllUser(){
        List<User> users= this.userServicer.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }




}
