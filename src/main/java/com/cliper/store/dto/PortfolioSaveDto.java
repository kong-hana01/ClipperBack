package com.cliper.store.dto;

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

    private String title;
    private String agency;
    private String introduction;
    private String contents;
    @JsonFormat(pattern = datePattern)
    private Date date;

    @Builder
    public PortfolioSaveDto(String title, String agency, String introduction, String contents, Date date) {
        this.title = title;
        this.agency = agency;
        this.introduction = introduction;
        this.contents = contents;
        this.date = date;
    }

    public Portfolio toEntity() {
        return Portfolio.builder()
                .title(title)
                .agency(agency)
                .introduction(introduction)
                .contents(contents)
                .date(date)
                .build();
    }
}
