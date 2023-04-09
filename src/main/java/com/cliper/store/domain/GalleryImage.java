package com.cliper.store.domain;

import com.cliper.store.dto.ClipperImageDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@Entity(name = "GALLERY_IMAGE")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicInsert
public class GalleryImage implements ClipperImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gallery_image_id")
    private int galleryImageId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "image_id")
    private Image image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gallery_id", referencedColumnName = "gallery_id")
    private Gallery gallery;

    @Builder
    public GalleryImage(Image image, Gallery gallery) {
        this.image = image;
        this.gallery = gallery;
    }

    public ClipperImageDto toDto() {
        return ClipperImageDto.builder()
                .fileName(image.getFileName())
                .isThumbnail(image.isThumbnail())
                .build();
    }
}
