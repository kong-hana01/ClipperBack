package com.cliper.store.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ClipperImageDto {

    private static final String imagePath = "file:///C:/Users/user/Desktop/clipper/img/";
    private String fileName;
    private boolean isThumbnail;

    @Builder
    public ClipperImageDto(String fileName, boolean isThumbnail) {
        this.fileName = imagePath + fileName;
        this.isThumbnail = isThumbnail;
    }
}
