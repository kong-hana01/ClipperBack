package com.cliper.store.domain;

import com.cliper.store.dto.GalleryDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.validation.ObjectError;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "GALLERY")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicInsert
public class Gallery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gallery_id")
    private int galleryId;

    @Column(length = 45, nullable = false, name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @OneToMany
    @JoinColumn(name = "gallery_id")
    private List<GalleryImage> galleryImages = new ArrayList<>();

    @Builder
    public Gallery(int galleryId, String title, String contents, Date date) {
        this.galleryId = galleryId;
        this.title = title;
        this.contents = contents;
        this.date = date;
    }

    public GalleryDto toDto() {
        return GalleryDto.builder()
                .galleryId(galleryId)
                .title(title)
                .contents(contents)
                .date(date)
                .galleryImages(galleryImages)
                .build();
    }
}
