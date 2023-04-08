package com.cliper.store.controller;

import com.cliper.store.dto.GallerySaveDto;
import com.cliper.store.service.GalleryService;
import com.cliper.store.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("")
public class Controller {

    @Autowired
    GalleryService galleryService;

    @Autowired
    PortfolioService portfolioService;

    // 갤러리 조회
    @GetMapping("/gallery")
    @ResponseBody
    public ResponseEntity<Object> getGallery() throws Exception {
        return new ResponseEntity<>(galleryService.getGallery(), HttpStatus.OK);
    }

    // 포트폴리오 조회
    @GetMapping("/portfolio")
    @ResponseBody
    public ResponseEntity<Object> getPortfolio() throws Exception {
        return new ResponseEntity<>(portfolioService.getPortfolio(), HttpStatus.OK);
    }

    // 갤러리 생성
    @PostMapping("/gallery/create")
    @ResponseBody
    public ResponseEntity<Object> save(@RequestPart(value = "data") GallerySaveDto gallerySaveDto, @RequestPart(value = "image") List<MultipartFile> multipartFiles) {
        return new ResponseEntity<>(galleryService.saveGallery(gallerySaveDto, multipartFiles), HttpStatus.OK);
    }
}
