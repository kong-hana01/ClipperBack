package com.cliper.store.service;

import com.cliper.store.dto.GallerySaveDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GalleryService {

    Object getGallery();
    Object saveGallery(GallerySaveDto gallerySaveDto, List<MultipartFile> files);
    Object updateGallery(int galleryId, GallerySaveDto gallerySaveDto, List<MultipartFile> files);
}
