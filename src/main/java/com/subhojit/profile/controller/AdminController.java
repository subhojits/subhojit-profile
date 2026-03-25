package com.subhojit.profile.controller;

import com.subhojit.profile.model.GalleryItemForm;
import com.subhojit.profile.model.ProfileUpdateForm;
import com.subhojit.profile.service.GalleryService;
import com.subhojit.profile.service.ProfileService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    private static final String ADMIN_AUTH_SESSION_KEY = "adminAuthenticated";

    private final ProfileService profileService;
    private final GalleryService galleryService;
    private final String magicPassword;

    public AdminController(
            ProfileService profileService,
            GalleryService galleryService,
            @Value("${admin.magic-password}") String magicPassword
    ) {
        this.profileService = profileService;
        this.galleryService = galleryService;
        this.magicPassword = magicPassword;
    }

    @GetMapping("/admin-login")
    public String adminLogin(
            @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "logout", required = false) String logout,
            Model model
    ) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "admin-login";
    }

    @PostMapping("/admin-login")
    public String adminLoginSubmit(@RequestParam("password") String password, HttpSession session) {
        if (magicPassword.equals(password)) {
            session.setAttribute(ADMIN_AUTH_SESSION_KEY, true);
            return "redirect:/admin";
        }
        return "redirect:/admin-login?error=1";
    }

    @PostMapping("/admin-logout")
    public String adminLogout(HttpSession session) {
        session.removeAttribute(ADMIN_AUTH_SESSION_KEY);
        return "redirect:/admin-login?logout=1";
    }

    @GetMapping("/admin")
    public String admin(Model model, @RequestParam(name = "status", required = false) String status, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/admin-login";
        }
        model.addAttribute("profile", profileService.getProfile());
        model.addAttribute("galleryItems", galleryService.getGalleryItems());
        model.addAttribute("status", status == null ? "" : status);
        return "admin";
    }

    @PostMapping("/admin/profile")
    public String updateProfile(@ModelAttribute ProfileUpdateForm form, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/admin-login";
        }
        profileService.updateProfile(form);
        return "redirect:/admin?status=profile-updated";
    }

    @PostMapping("/admin/gallery")
    public String addGalleryItem(@ModelAttribute GalleryItemForm form, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/admin-login";
        }
        galleryService.addItem(form);
        return "redirect:/admin?status=gallery-added";
    }

    @PostMapping("/admin/gallery/{id}")
    public String updateGalleryItem(@PathVariable Long id, @ModelAttribute GalleryItemForm form, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/admin-login";
        }
        galleryService.updateItem(id, form);
        return "redirect:/admin?status=gallery-updated";
    }

    @PostMapping("/admin/gallery/{id}/delete")
    public String deleteGalleryItem(@PathVariable Long id, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/admin-login";
        }
        galleryService.deleteItem(id);
        return "redirect:/admin?status=gallery-deleted";
    }

    private boolean isAuthenticated(HttpSession session) {
        Object auth = session.getAttribute(ADMIN_AUTH_SESSION_KEY);
        return auth instanceof Boolean b && b;
    }
}
