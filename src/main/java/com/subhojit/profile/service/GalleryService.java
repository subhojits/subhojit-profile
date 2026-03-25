package com.subhojit.profile.service;

import com.subhojit.profile.model.GalleryItem;
import com.subhojit.profile.model.GalleryItemForm;
import com.subhojit.profile.repository.GalleryItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryService {

    private final GalleryItemRepository galleryItemRepository;

    public GalleryService(GalleryItemRepository galleryItemRepository) {
        this.galleryItemRepository = galleryItemRepository;
    }

    public List<GalleryItem> getGalleryItems() {
        return galleryItemRepository.findAllByOrderByDisplayOrderAscIdAsc();
    }

    public void addItem(GalleryItemForm form) {
        GalleryItem item = new GalleryItem();
        apply(item, form);
        galleryItemRepository.save(item);
    }

    public void updateItem(Long id, GalleryItemForm form) {
        GalleryItem item = galleryItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gallery item not found: " + id));
        apply(item, form);
        galleryItemRepository.save(item);
    }

    public void deleteItem(Long id) {
        galleryItemRepository.deleteById(id);
    }

    private void apply(GalleryItem item, GalleryItemForm form) {
        item.setTitle(normalize(form.getTitle()));
        item.setSubtitle(normalize(form.getSubtitle()));
        item.setDescription(normalize(form.getDescription()));
        item.setUrl(normalize(form.getUrl()));
        item.setCategory(normalize(form.getCategory()));
        item.setDisplayOrder(form.getDisplayOrder() == null ? 0 : form.getDisplayOrder());
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim();
    }
}
