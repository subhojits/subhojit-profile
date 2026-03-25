package com.subhojit.profile.controller;

import com.subhojit.profile.model.GalleryItemForm;
import com.subhojit.profile.model.ProfileUpdateForm;
import com.subhojit.profile.service.GalleryService;
import com.subhojit.profile.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    private final ProfileService profileService;
    private final GalleryService galleryService;

    public AdminController(ProfileService profileService, GalleryService galleryService) {
        this.profileService = profileService;
        this.galleryService = galleryService;
    }

    @GetMapping("/admin")
    public String admin(Model model, @RequestParam(name = "status", required = false) String status) {
        model.addAttribute("profile", profileService.getProfile());
        model.addAttribute("galleryItems", galleryService.getGalleryItems());
        model.addAttribute("status", status == null ? "" : status);
        return "admin";
    }

    @PostMapping("/admin/profile")
    public String updateProfile(@ModelAttribute ProfileUpdateForm form) {
        profileService.updateProfile(form);
        return "redirect:/admin?status=profile-updated";
    }

    @PostMapping("/admin/gallery")
    public String addGalleryItem(@ModelAttribute GalleryItemForm form) {
        galleryService.addItem(form);
        return "redirect:/admin?status=gallery-added";
    }

    @PostMapping("/admin/gallery/{id}")
    public String updateGalleryItem(@PathVariable Long id, @ModelAttribute GalleryItemForm form) {
        galleryService.updateItem(id, form);
        return "redirect:/admin?status=gallery-updated";
    }

    @PostMapping("/admin/gallery/{id}/delete")
    public String deleteGalleryItem(@PathVariable Long id) {
        galleryService.deleteItem(id);
        return "redirect:/admin?status=gallery-deleted";
    }
}
