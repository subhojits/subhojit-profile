package com.subhojit.profile.controller;

import com.subhojit.profile.model.EducationEntry;
import com.subhojit.profile.model.ExperienceEntry;
import com.subhojit.profile.service.GalleryService;
import com.subhojit.profile.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProfileController {

    private final ProfileService profileService;
    private final GalleryService galleryService;

    public ProfileController(ProfileService profileService, GalleryService galleryService) {
        this.profileService = profileService;
        this.galleryService = galleryService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("profile", profileService.getProfile());
        model.addAttribute("focusAreas", List.of(
                "SAP Integration Suite and SAP Event Mesh",
                "PI/PO to Cloud Integration migration",
                "SAP BTP administration, security and identity setup",
                "API management and event-driven integration patterns",
                "Spring Boot and Kafka for enterprise integrations"
        ));
        model.addAttribute("topSkills", List.of(
                "Apigee API Management",
                "Apache Kafka",
                "Spring Boot",
                "SAP Integration Suite",
                "SAP API Management",
                "Groovy",
                "XSLT",
                "OData",
                "JSON",
                "XML"
        ));
        model.addAttribute("certifications", List.of(
                "SAP Certified Professional - Solution Architect - SAP BTP (P_BTPA_2408)",
                "Solace Certified Solutions Consultant",
                "Solace Certified Event Driven Integration Architect - SAP",
                "Kong Gateway Foundations",
                "API Design and Fundamentals of Google Cloud's Apigee API Platform",
                "Discovering the Foundations of SAP Commerce Cloud and SAP S/4HANA Cloud Integrations"
        ));
        model.addAttribute("experiences", List.of(
                new ExperienceEntry(
                        "Senior Consultant",
                        "SAP Labs India",
                        "January 2020 - Present",
                        "Gurugram, Haryana, India",
                        "Leading enterprise integration engagements across SAP landscapes, cloud migration, and integration architecture."
                ),
                new ExperienceEntry(
                        "Consultant",
                        "SAP Labs India",
                        "September 2010 - January 2020",
                        "India",
                        "Worked as an integration plus application consultant with strong delivery focus."
                ),
                new ExperienceEntry(
                        "Software Developer",
                        "IBM",
                        "September 2006 - September 2010",
                        "India",
                        "Worked as a SAP PI and ABAP developer in enterprise implementation environments."
                )
        ));
        model.addAttribute("education", List.of(
                new EducationEntry(
                        "Visvesvaraya Technological University",
                        "Bachelor of Engineering (Information Technology)",
                        "2000 - 2004"
                ),
                new EducationEntry(
                        "St. Augustine Day School, Shyamnagar",
                        "10th Standard",
                        "1983 - 1997"
                )
        ));
        model.addAttribute("languages", List.of(
                "English (Full Professional)",
                "Hindi (Limited Working)",
                "Bengali (Native or Bilingual)"
        ));
        model.addAttribute("galleryItems", galleryService.getGalleryItems());
        return "index";
    }
}
