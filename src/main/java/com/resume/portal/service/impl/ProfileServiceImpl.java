package com.resume.portal.service.impl;

import com.resume.portal.dto.ProfileDTO;
import com.resume.portal.exception.JobPortalException;
import com.resume.portal.model.Profile;
import com.resume.portal.repository.ProfileRepository;
import com.resume.portal.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfileRepository profileRepository;
    @Override
    public Long createProfile(String email) throws JobPortalException {
        Profile profile = new Profile();
        profile.setEmail(email);
        profile.setSkills(new ArrayList<>());
        profile.setExperiences(new ArrayList<>());
        profile.setCertifications(new ArrayList<>());
        Profile savedProfile = profileRepository.save(profile);
        return savedProfile.getId();
    }

    @Override
    public ProfileDTO getProfile(Long profileId) throws JobPortalException {
        return  profileRepository.findById(profileId).orElseThrow(()->new JobPortalException("Profile not found")).toDTO();

    }

    @Override
    public ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalException {
        profileRepository.findById(profileDTO.getId()).orElseThrow(()->new JobPortalException("Profile not found"));
        profileRepository.save(profileDTO.toEntity());
        return profileDTO;
    }
}
