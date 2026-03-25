package com.subhojit.profile.model;

public record ExperienceEntry(
        String role,
        String company,
        String duration,
        String location,
        String details
) {
}
