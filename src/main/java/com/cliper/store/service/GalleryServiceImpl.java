package com.cliper.store.service;

import com.cliper.store.domain.Gallery;
import com.cliper.store.repository.GalleryRepository;
import com.cliper.store.response.ExceptionCode;
import com.cliper.store.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private final GalleryRepository galleryRepository;

    @Override
    public Object getGallery() {
        List<Gallery> galleries = galleryRepository.findAll();
        return new Response(ExceptionCode.BOARD_GET_OK, galleries);
    }
}
