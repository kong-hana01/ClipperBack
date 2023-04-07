package com.cliper.store.service;

import com.cliper.store.domain.Portfolio;
import com.cliper.store.dto.GalleryDto;
import com.cliper.store.dto.PortfolioDto;
import com.cliper.store.repository.PortfolioRepository;
import com.cliper.store.response.ExceptionCode;
import com.cliper.store.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private final PortfolioRepository portfolioRepository;

    @Override
    public Object getPortfolio() {
        List<Portfolio> portfolios = portfolioRepository.findAll();
        List<PortfolioDto> galleryDtos = portfolios.stream()
                .map(Portfolio::toDto)
                .collect(Collectors.toList());
        return new Response(ExceptionCode.BOARD_GET_OK, galleryDtos);
    }
}
