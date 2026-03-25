package com.subhojit.profile.config;

import com.subhojit.profile.model.GalleryItem;
import com.subhojit.profile.model.Profile;
import com.subhojit.profile.repository.GalleryItemRepository;
import com.subhojit.profile.repository.ProfileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfileDataInitializer {

    @Bean
    CommandLineRunner seedProfile(ProfileRepository profileRepository, GalleryItemRepository galleryItemRepository) {
        return args -> {
            if (profileRepository.count() == 0) {
                Profile profile = new Profile();
                profile.setFullName("Subhojit Saha");
                profile.setHeadline("Senior SAP Integration Consultant and SAP BTP Analyst at SAP");
                profile.setLocation("Gurugram, Haryana, India");
                profile.setSummary("Integration expert with deep experience in SAP Integration Suite, PI/PO, API Management, cloud migrations, and enterprise-grade integration architecture.");
                profile.setPrimaryEmail("subhojit.sap@outlook.com");
                profile.setSecondaryEmail("subhojits@gmail.com");
                profile.setLinkedinUrl("https://www.linkedin.com/in/%F0%9F%A7%BFsubhojit-s-840996b9/");
                profile.setPublicationUrl("https://lnkd.in/gXMB-n-T");
                profile.setCredlyUrl("https://www.credly.com/users/subhojit-saha.a4f3ea91/badges#credly");
                profileRepository.save(profile);
            }

            if (galleryItemRepository.count() == 0) {
                GalleryItem item1 = new GalleryItem();
                item1.setTitle("Enterprise Integration Publications");
                item1.setSubtitle("Thought Leadership");
                item1.setDescription("A collection of write-ups and practical integration notes from real-world SAP and API projects.");
                item1.setUrl("https://lnkd.in/gXMB-n-T");
                item1.setCategory("Publication");
                item1.setDisplayOrder(1);
                galleryItemRepository.save(item1);

                GalleryItem item2 = new GalleryItem();
                item2.setTitle("Credly Certifications");
                item2.setSubtitle("Professional Credentials");
                item2.setDescription("Verified badges covering SAP, API Management, and event-driven architecture credentials.");
                item2.setUrl("https://www.credly.com/users/subhojit-saha.a4f3ea91/badges#credly");
                item2.setCategory("Certification");
                item2.setDisplayOrder(2);
                galleryItemRepository.save(item2);

                GalleryItem item3 = new GalleryItem();
                item3.setTitle("LinkedIn Profile");
                item3.setSubtitle("Professional Journey");
                item3.setDescription("Experience timeline, achievements, and ongoing work in enterprise integration and SAP BTP.");
                item3.setUrl("https://www.linkedin.com/in/%F0%9F%A7%BFsubhojit-s-840996b9/");
                item3.setCategory("Profile");
                item3.setDisplayOrder(3);
                galleryItemRepository.save(item3);
            }
        };
    }
}
