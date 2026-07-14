package com.resume.portal.controller;

import com.resume.portal.dto.ProfileDTO;
import com.resume.portal.dto.ResponseDTO;
import com.resume.portal.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable Long id) throws Exception {

        return new ResponseEntity<>(profileService.getProfile(id), HttpStatus.OK);
    }

    @PutMapping ("/update")
    public ResponseEntity<ProfileDTO> updateProfile(@RequestBody ProfileDTO profileDTO) throws Exception {

        return new ResponseEntity<>(profileService.updateProfile(profileDTO), HttpStatus.OK);
    }

}
