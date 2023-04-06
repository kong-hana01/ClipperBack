package com.cliper.store.domain;

import com.cliper.store.dto.GalleryImageDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@Entity(name = "PORTFOLIO_IMAGE")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicInsert
public class PortfolioImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_image_id")
    private int portfolioImageId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "image_id")
    private Image image;

    @Column(name = "portfolio_id")
    private int portfolioId;

    @Builder
    public PortfolioImage(int portfolioImageId, Image image, int portfolioId) {
        this.portfolioImageId = portfolioImageId;
        this.image = image;
        this.portfolioId = portfolioId;
    }
}
