package com.cliper.store.service;

import com.cliper.store.domain.*;
import com.cliper.store.dto.ClipperImageDto;
import com.cliper.store.dto.GalleryDto;
import com.cliper.store.dto.GallerySaveDto;
import com.cliper.store.repository.GalleryImageRepository;
import com.cliper.store.repository.GalleryRepository;
import com.cliper.store.service.handler.ImageHandler;
import com.cliper.store.service.response.ExceptionCodeProd;
import com.cliper.store.service.response.Response;
import com.cliper.store.service.response.ResponseEmpty;
import com.cliper.store.service.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class GalleryServiceImpl implements GalleryService {

    public static final int EXISTS_STATUS = 1;
    @Autowired
    private final GalleryRepository galleryRepository;

    @Autowired
    private final GalleryImageRepository galleryImageRepository;

    private final ImageHandler imageHandler = new ImageHandler();

    @Override
    public Object getGallery() {
        List<Gallery> galleries = galleryRepository.findAllByStatusEqualsOrderByDateDesc(EXISTS_STATUS);
        List<GalleryDto> galleryDtos = galleries.stream()
                .map(Gallery::toDto)
                .collect(Collectors.toList());
        return new Response(ExceptionCodeProd.GALLERY_GET_OK, galleryDtos);
    }

    @Override
    public Object saveGallery(GallerySaveDto gallerySaveDto, List<MultipartFile> files) {
        Gallery gallery = gallerySaveDto.toEntity();
        try {
            List<Image> images = imageHandler.parseImageInfo(files);
            List<GalleryImage> galleryImages = saveImages(gallery, images);

            GalleryDto galleryDto = generateGallertDto(gallery, galleryImages);
            return new Response(ExceptionCodeProd.GALLERY_CREATE_OK, galleryDto);
        } catch (IllegalArgumentException exception) {
            ResponseMessage responseMessage = ResponseMessage.findByMessage(exception.getMessage());
            return new ResponseEmpty(ExceptionCodeProd.findByResponseMessage(responseMessage));
        }
    }

    private static GalleryDto generateGallertDto(Gallery gallery, List<GalleryImage> galleryImages) {
        List<ClipperImageDto> clipperImageDtos = galleryImages.stream()
                .map(galleryImage -> galleryImage.toDto())
                .collect(Collectors.toList());
        GalleryDto galleryDto = gallery.toDto();

        galleryDto.setClipperImageDtos(clipperImageDtos);
        return galleryDto;
    }

    private List<GalleryImage> saveImages(Gallery gallery, List<Image> images) {
        List<GalleryImage> galleryImages = new ArrayList<>();
        for (Image image : images) {
            GalleryImage galleryImage = GalleryImage.builder()
                    .gallery(gallery)
                    .image(image)
                    .build();
            galleryImageRepository.save(galleryImage);
            galleryImages.add(galleryImage);
        }
        return galleryImages;
    }

    @Override
    public Object updateGallery(int galleryId, GallerySaveDto gallerySaveDto, List<MultipartFile> files) {
        Gallery gallery = gallerySaveDto.toEntity();
        Optional<Gallery> lastGalleryOptional = galleryRepository.findById(galleryId);
        if (lastGalleryOptional.isEmpty() || lastGalleryOptional.get().getStatus() == 0) {
            return new ResponseEmpty(ExceptionCodeProd.GALLERY_UPDATE_ERROR_INVALID_MATCH_GALLERY);
        }
        try {
            List<Image> images = imageHandler.parseImageInfo(files);
            List<GalleryImage> galleryImages = saveImages(gallery, images);
            delete(lastGalleryOptional.get());

            GalleryDto galleryDto = generateGallertDto(gallery, galleryImages);
            return new Response(ExceptionCodeProd.GALLERY_UPDATE_OK, galleryDto);
        } catch (IllegalArgumentException exception) {
            ResponseMessage responseMessage = ResponseMessage.findByMessage(exception.getMessage());
            return new ResponseEmpty(ExceptionCodeProd.findByResponseMessage(responseMessage));
        }
    }

    private void delete(Gallery gallery) {
        gallery.delete();
        List<Image> imageForDelete = gallery.getGalleryImages()
                .stream()
                .map(GalleryImage::getImage)
                .collect(Collectors.toList());
        imageHandler.deleteImages(imageForDelete);
    }

    @Override
    public Object deleteGallery(int galleryId) {
        Optional<Gallery> lastGalleryOptional = galleryRepository.findById(galleryId);
        if (lastGalleryOptional.isEmpty() || lastGalleryOptional.get().getStatus() == 0) {
            return new ResponseEmpty(ExceptionCodeProd.GALLERY_UPDATE_ERROR_INVALID_MATCH_GALLERY);
        }
        delete(lastGalleryOptional.get());
        return new ResponseEmpty(ExceptionCodeProd.GALLERY_DELETE_OK);
    }
}
