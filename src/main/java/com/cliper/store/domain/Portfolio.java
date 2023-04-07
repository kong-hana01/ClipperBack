package com.cliper.store.domain;

import com.cliper.store.dto.GalleryDto;
import com.cliper.store.dto.PortfolioDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "PORTFOLIO")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicInsert
public class Portfolio extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    private int portfolioId;

    @Column(name = "portfolio_category")
    private String portfolioCategory;

    @Column(name = "agency")
    private String agency;

    @Column(name = "contents")
    private String contents;

    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @OneToMany
    @JoinColumn(name = "portfolio_id")
    private List<PortfolioImage> portfolioImages = new ArrayList<>();

    @Builder
    public Portfolio(int portfolioId, String portfolioCategory, String agency, String contents, Date date) {
        this.portfolioId = portfolioId;
        this.portfolioCategory = portfolioCategory;
        this.agency = agency;
        this.contents = contents;
        this.date = date;
    }

    public PortfolioDto toDto() {
        return PortfolioDto.builder()
                .portfolioId(portfolioId)
                .portfolioCategory(portfolioCategory)
                .agency(agency)
                .contents(contents)
                .date(date)
                .portfolioImages(portfolioImages)
                .build();
    }
}
