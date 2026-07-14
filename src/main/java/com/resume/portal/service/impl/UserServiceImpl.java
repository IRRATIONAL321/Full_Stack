package com.resume.portal.service.impl;

import com.resume.portal.dto.LoginDTO;
import com.resume.portal.dto.ResponseDTO;
import com.resume.portal.dto.UserDTO;
import com.resume.portal.exception.JobPortalException;
import com.resume.portal.exception.ResourceNotFoundException;
import com.resume.portal.model.OTP;
import com.resume.portal.model.User;
import com.resume.portal.repository.OTPRepository;
import com.resume.portal.repository.UserRepository;
import com.resume.portal.service.ProfileService;
import com.resume.portal.service.UserService;
import com.resume.portal.utility.Utilities;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private JavaMailSender javaMailSender;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO register(UserDTO userDTO) throws JobPortalException {
        userDTO.setProfileId(profileService.createProfile(userDTO.getEmail()));

        User user=userDTO.toEntity();
        userRepository.save(user);
        return user.toDTO();
    }

    @Override
    public UserDTO loginUser(LoginDTO logindto) throws JobPortalException{
        User user=userRepository.findByEmail(logindto.getEmail()).orElseThrow(()-> new JobPortalException("User not found"));
        if(!user.getPassword().equals(logindto.getPassword())){
            throw new JobPortalException("INVALID_CREDENTIALS");
        }
       return user.toDTO();
    }

    @Override
    public Boolean sendOtp(String email) throws Exception {
        userRepository.findByEmail(email).orElseThrow(()-> new
                JobPortalException("User not found"));
        MimeMessage mm = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mm,true,"UTF-8");
        message.setTo(email);
        message.setSubject("Your otp code");
        String genotp= Utilities.generateOTP();
        OTP otp = new OTP(email,genotp, LocalDateTime.now());
        otpRepository.save(otp);
        message.setText("Your code is"+genotp,false);
        javaMailSender.send(mm);
        return true;
    }

    @Override
    public Boolean verifyOtp(String email,String otp) throws JobPortalException {
        OTP otpEntity=otpRepository.findById(email).orElseThrow(()-> new
                JobPortalException("OTP not found"));
        if(!otpEntity.getOtp().equals(otp))throw new JobPortalException("INVALID_OTP");
        return true;

    }

    @Override
    public ResponseDTO changePassword(LoginDTO loginDTO) throws JobPortalException {
        User user=userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()-> new
                JobPortalException("User not found"));
        user.setPassword(loginDTO.getPassword());
        userRepository.save(user);
        return new ResponseDTO("Password changed");

    }
}
