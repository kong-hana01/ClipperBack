package com.cliper.store.service;

import com.cliper.store.domain.*;
import com.cliper.store.dto.GallerySaveDto;
import com.cliper.store.dto.PortfolioDto;
import com.cliper.store.dto.PortfolioSaveDto;
import com.cliper.store.repository.PortfolioImageRepository;
import com.cliper.store.repository.PortfolioRepository;
import com.cliper.store.service.handler.ImageHandler;
import com.cliper.store.service.response.ExceptionCodeProd;
import com.cliper.store.service.response.Response;
import com.cliper.store.service.response.ResponseEmpty;
import com.cliper.store.service.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private final PortfolioRepository portfolioRepository;

    @Autowired
    private final PortfolioImageRepository portfolioImageRepository;

    private final ImageHandler imageHandler = new ImageHandler();

    @Override
    public Object getPortfolio() {
        List<Portfolio> portfolios = portfolioRepository.findAll();
        List<PortfolioDto> galleryDtos = portfolios.stream()
                .map(Portfolio::toDto)
                .collect(Collectors.toList());
        return new Response(ExceptionCodeProd.PORTFOLIO_GET_OK, galleryDtos);
    }

    @Override
    public Object savePortfolio(PortfolioSaveDto portfolioSaveDto, List<MultipartFile> files) {
        Portfolio portfolio = portfolioSaveDto.toEntity();
        try {
            List<Image> images = imageHandler.parseImageInfo(files);
            saveImages(portfolio, images);
        } catch (IllegalArgumentException exception) {
            ResponseMessage responseMessage = ResponseMessage.findByMessage(exception.getMessage());
            return new ResponseEmpty(ExceptionCodeProd.findByResponseMessage(responseMessage));
        }
        return new ResponseEmpty(ExceptionCodeProd.PORTFOLIO_CREATE_OK);
    }

    private void saveImages(Portfolio portfolio, List<Image> images) {
        for (Image image : images) {
            PortfolioImage portfolioImage = PortfolioImage.builder()
                    .portfolio(portfolio)
                    .image(image)
                    .build();
            portfolioImageRepository.save(portfolioImage);
        }
    }
}
