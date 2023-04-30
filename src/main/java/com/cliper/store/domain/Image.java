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
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int imageId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "is_thumbnail")
    private boolean isThumbnail;

    @Builder
    public Image(String fileName, boolean isThumbnail) {
        this.fileName = fileName;
        this.isThumbnail = isThumbnail;
    }
}
