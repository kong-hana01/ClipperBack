package com.cliper.store.controller;

import com.cliper.store.service.GalleryService;
import com.cliper.store.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
