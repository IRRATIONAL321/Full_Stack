package com.resume.portal.service.impl;

import com.resume.portal.dto.UserDTO;
import com.resume.portal.exception.ResourceNotFoundException;
import com.resume.portal.repository.UserRepository;
import com.resume.portal.service.UserService;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = convertDTOToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertEntityToDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return convertEntityToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());

        User updatedUser = userRepository.update(user);
        return convertEntityToDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.delete(id);
    }

    private UserDTO convertEntityToDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPhone());
    }

    private User convertDTOToEntity(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), userDTO.getPhone());
    }
}
