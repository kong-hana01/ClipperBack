package com.cliper.store.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ClipperImageDto {

    private int galleryImageId;
    private int gallery_id;
    private int imageId;
    private String fileName;
    private boolean isThumbnail;

    @Value("${imagePath}")
    private String filePath;

    @Builder
    public ClipperImageDto(int galleryImageId, int gallery_id, int imageId, String fileName, boolean isThumbnail) {
        this.galleryImageId = galleryImageId;
        this.gallery_id = gallery_id;
        this.imageId = imageId;
        this.fileName = filePath + fileName;
        this.isThumbnail = isThumbnail;
    }
}
