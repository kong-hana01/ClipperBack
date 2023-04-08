package com.cliper.store.service;

import com.cliper.store.domain.Gallery;
import com.cliper.store.domain.GalleryImage;
import com.cliper.store.domain.Image;
import com.cliper.store.dto.GalleryDto;
import com.cliper.store.dto.GallerySaveDto;
import com.cliper.store.repository.GalleryImageRepository;
import com.cliper.store.repository.GalleryRepository;
import com.cliper.store.response.ExceptionCode;
import com.cliper.store.response.Response;
import com.cliper.store.response.ResponseEmpty;
import com.cliper.store.service.handler.ImageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private final GalleryRepository galleryRepository;

    @Autowired
    private final GalleryImageRepository galleryImageRepository;

    private final ImageHandler imageHandler = new ImageHandler();

    @Override
    public Object getGallery() {
        List<Gallery> galleries = galleryRepository.findAll();
        List<GalleryDto> galleryDtos = galleries.stream()
                .map(Gallery::toDto)
                .collect(Collectors.toList());
        return new Response(ExceptionCode.BOARD_GET_OK, galleryDtos);
    }

    @Override
    public Object saveGallery(GallerySaveDto gallerySaveDto, List<MultipartFile> files) {
        Gallery gallery = gallerySaveDto.toEntity();
        try {
            List<Image> images = imageHandler.parseImageInfo(files);
            for (Image image : images) {
                GalleryImage galleryImage = GalleryImage.builder()
                        .gallery(gallery)
                        .image(image)
                        .build();
                galleryImageRepository.save(galleryImage);
            }
        } catch (IllegalArgumentException exception) {
            return new ResponseEmpty(ExceptionCode.BOARD_CREATE_ERROR);
        }
        return new ResponseEmpty(ExceptionCode.BOARD_CREATE_OK);
    }
}
