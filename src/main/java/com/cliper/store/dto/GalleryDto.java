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
    private String title;
    private String contents;
    private Date date;
    private List<GalleryImageDto> galleryImageDtos;

    @Builder
    public GalleryDto(int galleryId, String title, String contents, Date date, List<GalleryImage> galleryImages) {
        this.galleryId = galleryId;
        this.title = title;
        this.contents = contents;
        this.date = date;
        this.galleryImageDtos = galleryImages.stream()
                .map(GalleryImage::toDto)
                .collect(Collectors.toList());
    }
}
