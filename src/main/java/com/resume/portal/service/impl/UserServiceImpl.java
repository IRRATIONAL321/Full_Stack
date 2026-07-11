package com.resume.portal.service.impl;

import com.resume.portal.dto.UserDTO;
import com.resume.portal.exception.ResourceNotFoundException;
import com.resume.portal.model.User;
import com.resume.portal.repository.UserRepository;
import com.resume.portal.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO register(UserDTO userDTO) {

        User user=userDTO.toEntity();
        userRepository.save(user);
        return user.toDTO();
    }


}
