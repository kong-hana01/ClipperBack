package com.cliper.store.dto;

import lombok.*;

import static com.cliper.store.utils.Paths.IMAGE_PATH;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ClipperImageDto {

    private String fileName;
    private boolean isThumbnail;

    @Builder
    public ClipperImageDto(String fileName, boolean isThumbnail) {
        this.fileName = IMAGE_PATH + fileName;
        this.isThumbnail = isThumbnail;
    }
}
