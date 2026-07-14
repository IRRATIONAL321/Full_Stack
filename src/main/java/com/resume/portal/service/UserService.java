package com.resume.portal.service;

import com.resume.portal.dto.LoginDTO;
import com.resume.portal.dto.ResponseDTO;
import com.resume.portal.dto.UserDTO;
import com.resume.portal.exception.JobPortalException;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    UserDTO register(UserDTO userdto) throws JobPortalException;
    UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException;

    Boolean sendOtp(String email) throws Exception;

    Boolean verifyOtp(String email,String otp) throws JobPortalException;

    ResponseDTO changePassword(LoginDTO loginDTO) throws  JobPortalException;
}
