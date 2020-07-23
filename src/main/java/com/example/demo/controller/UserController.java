

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DTOUSer;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pangestu
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/all")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
       
    @GetMapping("/listUser")
    public  ResponseEntity<?> tampilDataUser(){
        List<User> user = userRepository.findAll();
        DTOUSer dtouser = new DTOUSer();
        dtouser.setDescripsi("Sukses");
        dtouser.setListuser(user);
        
        return ResponseEntity.ok(dtouser);
    }
    @PostMapping("/create")
    public User tambahUser(@RequestBody User userDetail){
        return userRepository.save(userDetail);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Integer idUser, @RequestBody User userDetail){
        User user = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException("User", "id", userDetail));
        
        user.setName(userDetail.getName());
        user.setUserName(userDetail.getUserName());
        user.setEmail(userDetail.getEmail());
        
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    
}
