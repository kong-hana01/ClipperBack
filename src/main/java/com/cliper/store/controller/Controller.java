package com.cliper.store.controller;

import com.cliper.store.dto.GallerySaveDto;
import com.cliper.store.dto.PortfolioSaveDto;
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
    public ResponseEntity<Object> getGallery() {
        return new ResponseEntity<>(galleryService.getGallery(), HttpStatus.OK);
    }

    // 포트폴리오 조회
    @GetMapping("/portfolio")
    @ResponseBody
    public ResponseEntity<Object> getPortfolio() {
        return new ResponseEntity<>(portfolioService.getPortfolio(), HttpStatus.OK);
    }

    // 갤러리 생성
    @PostMapping("/gallery/create")
    @ResponseBody
    public ResponseEntity<Object> saveGallery(@RequestPart(value = "data") GallerySaveDto gallerySaveDto, @RequestPart(value = "image") List<MultipartFile> multipartFiles) {
        return new ResponseEntity<>(galleryService.saveGallery(gallerySaveDto, multipartFiles), HttpStatus.OK);
    }

    // 갤러리 업데이트
    @PostMapping("/gallery/update/{galleryId}")
    @ResponseBody
    public ResponseEntity<Object> updateGallery(@PathVariable("galleryId") int galleryId, @RequestPart(value = "data") GallerySaveDto gallerySaveDto, @RequestPart(value = "image") List<MultipartFile> multipartFiles) {
        return new ResponseEntity<>(galleryService.updateGallery(galleryId, gallerySaveDto, multipartFiles), HttpStatus.OK);
    }

    // 갤러리 삭제
    @PostMapping("/gallery/delete/{galleryId}")
    @ResponseBody
    public ResponseEntity<Object> deleteGallery(@PathVariable("galleryId") int galleryId) {
        return new ResponseEntity<>(galleryService.deleteGallery(galleryId), HttpStatus.OK);
    }

    // 포트폴리오 생성
    @PostMapping("/portfolio/create")
    @ResponseBody
    public ResponseEntity<Object> savePortfolio(@RequestPart(value = "data") PortfolioSaveDto portfolioSaveDto, @RequestPart(value = "image") List<MultipartFile> multipartFiles) {
        return new ResponseEntity<>(portfolioService.savePortfolio(portfolioSaveDto, multipartFiles), HttpStatus.OK);
    }
}
