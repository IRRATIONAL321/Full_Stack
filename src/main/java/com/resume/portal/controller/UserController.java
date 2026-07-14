package com.resume.portal.controller;

import com.resume.portal.dto.LoginDTO;
import com.resume.portal.dto.ResponseDTO;
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
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) throws JobPortalException {
        userDTO = userService.register(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody LoginDTO loginDTO) throws JobPortalException {

        return new ResponseEntity<>(userService.loginUser(loginDTO), HttpStatus.OK);
    }

    @PostMapping("/changePass")
    public ResponseEntity<ResponseDTO> changePassword(@RequestBody LoginDTO loginDTO) throws JobPortalException {

        return new ResponseEntity<>(userService.changePassword(loginDTO), HttpStatus.OK);
    }
    @PostMapping("/sendOtp/{email}")
    public ResponseEntity<ResponseDTO> sendOtp(@PathVariable String email) throws Exception {
        userService.sendOtp(email);
        return new ResponseEntity<>(new ResponseDTO("Otp sent successfully"), HttpStatus.OK);
    }

    @GetMapping("/verifyOtp/{email}/{otp}")
    public ResponseEntity<ResponseDTO> verifyOtp(@PathVariable String email,@PathVariable String otp) throws Exception {
        userService.verifyOtp(email,otp);
        return new ResponseEntity<>(new ResponseDTO("Otp has been verified"), HttpStatus.OK);
    }


}
