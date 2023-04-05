package com.cliper.store.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@Entity(name = "IMAGE")
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

    @Column(name = "image_id")
    private int imageId;

    @Column(name = "gallery_id")
    private int gallery_id;

    @Builder
    public GalleryImage(int galleryImageId, int imageId, int gallery_id) {
        this.galleryImageId = galleryImageId;
        this.imageId = imageId;
        this.gallery_id = gallery_id;
    }
}
