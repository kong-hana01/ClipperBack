package com.cliper.store.dto;

import com.cliper.store.domain.GalleryImage;
import lombok.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GalleryDto {

    private int galleryId;
    private String galleryType;
    private String title;
    private String contents;
    private Date date;
    private List<ClipperImageDto> clipperImageDtos;

    @Builder
    public GalleryDto(int galleryId, String galleryType, String title, String contents, Date date, List<GalleryImage> galleryImages) {
        this.galleryId = galleryId;
        this.galleryType = galleryType;
        this.title = title;
        this.contents = contents;
        this.date = date;
        this.clipperImageDtos = galleryImages.stream()
                .map(GalleryImage::toDto)
                .collect(Collectors.toList());
    }
}
