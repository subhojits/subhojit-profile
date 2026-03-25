package com.subhojit.profile.service;

import com.subhojit.profile.model.Profile;
import com.subhojit.profile.model.ProfileUpdateForm;
import com.subhojit.profile.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile getProfile() {
        return profileRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Profile data not initialized"));
    }

    public void updateProfile(ProfileUpdateForm form) {
        Profile profile = getProfile();
        profile.setFullName(normalize(form.getFullName()));
        profile.setHeadline(normalize(form.getHeadline()));
        profile.setLocation(normalize(form.getLocation()));
        profile.setSummary(normalize(form.getSummary()));
        profile.setPrimaryEmail(normalize(form.getPrimaryEmail()));
        profile.setSecondaryEmail(normalize(form.getSecondaryEmail()));
        profile.setLinkedinUrl(normalize(form.getLinkedinUrl()));
        profile.setPublicationUrl(normalize(form.getPublicationUrl()));
        profile.setCredlyUrl(normalize(form.getCredlyUrl()));
        profileRepository.save(profile);
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim();
    }
}
