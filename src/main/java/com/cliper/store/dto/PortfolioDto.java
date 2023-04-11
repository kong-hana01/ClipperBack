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
    private String title;
    private String agency;
    private String introduction;
    private String contents;
    private Date date;
    private List<ClipperImageDto> clipperImageDtos;

    @Builder
    public PortfolioDto(int portfolioId, String title, String agency, String introduction, String contents, Date date, List<PortfolioImage> portfolioImages) {
        this.portfolioId = portfolioId;
        this.title = title;
        this.agency = agency;
        this.introduction = introduction;
        this.contents = contents;
        this.date = date;
        this.clipperImageDtos = portfolioImages.stream()
                .map(PortfolioImage::toDto)
                .collect(Collectors.toList());
    }
}
