package com.cliper.store.dto;

import com.cliper.store.domain.Gallery;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Date;

@ToString
@Setter
@Getter
@NoArgsConstructor
public class GalleryUpdateDto {

    private static final String datePattern = "yyyy-MM-dd";

    private int galleryId;
    private String title;
    private String contents;
    @JsonFormat(pattern = datePattern)
    private Date date;

    public Gallery toEntity() {
        return Gallery.builder()
                .galleryId(galleryId)
                .title(title)
                .contents(contents)
                .date(date)
                .build();
    }

    @Builder
    public GalleryUpdateDto(int galleryId, String title, String contents, Date date) {
        this.galleryId = galleryId;
        this.title = title;
        this.contents = contents;
        this.date = date;
    }
}
