package com.cliper.store.service;

import com.cliper.store.domain.Portfolio;
import com.cliper.store.dto.PortfolioSaveDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PortfolioService {

    Object getPortfolio();

    Object savePortfolio(PortfolioSaveDto portfolioSaveDto, List<MultipartFile> files);
}
