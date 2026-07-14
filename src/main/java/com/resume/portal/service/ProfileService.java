package com.resume.portal.service;

import com.resume.portal.dto.ProfileDTO;
import com.resume.portal.exception.JobPortalException;

public interface ProfileService {
    Long createProfile(String email) throws JobPortalException;
    ProfileDTO getProfile(Long profileId) throws JobPortalException;
    ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalException;

}
