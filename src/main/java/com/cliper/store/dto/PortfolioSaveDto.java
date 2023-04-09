package com.cliper.store.dto;

import com.cliper.store.domain.Gallery;
import com.cliper.store.domain.Portfolio;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Date;

@ToString
@Setter
@Getter
@NoArgsConstructor
public class PortfolioSaveDto {

    private static final String datePattern = "yyyy-MM-dd";

    private String portfolioCategory;
    private String agency;
    private String contents;
    @JsonFormat(pattern = datePattern)
    private Date date;

    @Builder
    public PortfolioSaveDto(String portfolioCategory, String agency, String contents, Date date) {
        this.portfolioCategory = portfolioCategory;
        this.agency = agency;
        this.contents = contents;
        this.date = date;
    }

    public Portfolio toEntity() {
        return Portfolio.builder()
                .portfolioCategory(portfolioCategory)
                .agency(agency)
                .contents(contents)
                .date(date)
                .build();
    }
}
