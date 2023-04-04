package com.cliper.store.controller;

import com.cliper.store.repository.GalleryRepository;
import com.cliper.store.service.GalleryService;
import com.cliper.store.service.GalleryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gallery")
public class GalleryController {

    @Autowired
    GalleryService galleryService;

    // 갤러리 조회
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> getGallery() throws Exception {
        return new ResponseEntity<>(galleryService.getGallery(), HttpStatus.OK);
    }
}
