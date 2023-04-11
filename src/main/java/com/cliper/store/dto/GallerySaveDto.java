package com.cliper.store.dto;

import com.cliper.store.domain.Gallery;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Date;

@ToString
@Setter
@Getter
@NoArgsConstructor
public class GallerySaveDto {

    private static final String datePattern = "yyyy-MM-dd";

    private String galleryType;
    private String title;
    private String contents;
    @JsonFormat(pattern = datePattern)
    private Date date;

    @Builder
    public GallerySaveDto(String galleryType, String title, String contents, Date date) {
        this.galleryType = galleryType;
        this.title = title;
        this.contents = contents;
        this.date = date;
    }

    public Gallery toEntity() {
        return Gallery.builder()
                .galleryType(galleryType)
                .title(title)
                .contents(contents)
                .date(date)
                .build();
    }
}
