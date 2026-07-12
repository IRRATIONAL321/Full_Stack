package com.resume.portal.service;

import com.resume.portal.dto.LoginDTO;
import com.resume.portal.dto.UserDTO;
import com.resume.portal.exception.JobPortalException;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    UserDTO register(UserDTO userdto);
    UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException;

}
