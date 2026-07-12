package com.resume.portal.controller;

import com.resume.portal.dto.LoginDTO;
import com.resume.portal.dto.UserDTO;
import com.resume.portal.exception.JobPortalException;
import com.resume.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        userDTO = userService.register(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody LoginDTO loginDTO) throws JobPortalException {

        return new ResponseEntity<>(userService.loginUser(loginDTO), HttpStatus.OK);
    }


}
