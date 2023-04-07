package com.cliper.store.dto;

import com.cliper.store.domain.PortfolioImage;
import lombok.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PortfolioDto {

    private int portfolioId;
    private String portfolioCategory;
    private String contents;
    private Date date;
    private List<ClipperImageDto> clipperImageDtos;

    @Builder
    public PortfolioDto(int portfolioId, String portfolioCategory, String contents, Date date, List<PortfolioImage> portfolioImages) {
        this.portfolioId = portfolioId;
        this.portfolioCategory = portfolioCategory;
        this.contents = contents;
        this.date = date;
        this.clipperImageDtos = portfolioImages.stream()
                .map(PortfolioImage::toDto)
                .collect(Collectors.toList());
    }
}
