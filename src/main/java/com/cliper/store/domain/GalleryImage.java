package com.cliper.store.domain;

import com.cliper.store.dto.GalleryImageDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@Entity(name = "GALLERY_IMAGE")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicInsert
public class GalleryImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gallery_image_id")
    private int galleryImageId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "image_id")
    private Image image;

    @Column(name = "gallery_id")
    private int gallery_id;

    @Builder
    public GalleryImage(int galleryImageId, Image image, int gallery_id) {
        this.galleryImageId = galleryImageId;
        this.image = image;
        this.gallery_id = gallery_id;
    }

    public GalleryImageDto toDto() {
        return GalleryImageDto.builder()
                .galleryImageId(galleryImageId)
                .imageId(image.getImageId())
                .fileName(image.getFileName())
                .isThumbnail(image.isThumbnail())
                .gallery_id(gallery_id)
                .build();
    }
}