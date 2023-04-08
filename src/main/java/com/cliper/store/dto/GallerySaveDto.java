package com.cliper.store.dto;

import com.cliper.store.domain.Gallery;
import com.cliper.store.domain.GalleryImage;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class GallerySaveDto {
    private String title;
    private String contents;
    private Date date;

    public Gallery toEntity() {
        return Gallery.builder()
                .title(title)
                .contents(contents)
                .date(date)
                .build();
    }
}
