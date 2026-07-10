package com.resume.portal.controller;

import com.resume.portal.dto.UserDTO;
import com.resume.portal.service.UserService;
import java.util.List;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserDTO createUser(UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    public UserDTO getUserById(Long id) {
        return userService.getUserById(id);
    }

    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }
}
