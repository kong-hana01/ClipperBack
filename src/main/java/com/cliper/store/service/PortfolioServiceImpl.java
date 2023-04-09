package com.cliper.store.service;

import com.cliper.store.domain.Image;
import com.cliper.store.domain.Portfolio;
import com.cliper.store.domain.PortfolioImage;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PortfolioServiceImpl implements PortfolioService {

    public static final int EXISTS_STATUS = 1;
    @Autowired
    private final PortfolioRepository portfolioRepository;

    @Autowired
    private final PortfolioImageRepository portfolioImageRepository;

    private final ImageHandler imageHandler = new ImageHandler();

    @Override
    public Object getPortfolio() {
        List<Portfolio> portfolios = portfolioRepository.findAllByStatusEqualsOrderByDateDesc(EXISTS_STATUS);
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

    @Override
    public Object updatePortfolio(int portfolioId, PortfolioSaveDto portfolioSaveDto, List<MultipartFile> files) {
        Portfolio portfolio = portfolioSaveDto.toEntity();
        Optional<Portfolio> lastPortfolioOptional = portfolioRepository.findById(portfolioId);
        if (lastPortfolioOptional.isEmpty() || lastPortfolioOptional.get().getStatus() == 0) {
            return new ResponseEmpty(ExceptionCodeProd.PORTFOLIO_UPDATE_ERROR_INVALID_MATCH_GALLERY);
        }
        try {
            List<Image> images = imageHandler.parseImageInfo(files);
            saveImages(portfolio, images);
            Portfolio lastPortfolio = lastPortfolioOptional.get();
            lastPortfolio.delete();
        } catch (IllegalArgumentException exception) {
            ResponseMessage responseMessage = ResponseMessage.findByMessage(exception.getMessage());
            return new ResponseEmpty(ExceptionCodeProd.findByResponseMessage(responseMessage));
        }
        return new ResponseEmpty(ExceptionCodeProd.PORTFOLIO_UPDATE_OK);
    }

    @Override
    public Object deletePortfolio(int portfolioId) {
        Optional<Portfolio> lastPortfolioOptional = portfolioRepository.findById(portfolioId);
        if (lastPortfolioOptional.isEmpty() || lastPortfolioOptional.get().getStatus() == 0) {
            return new ResponseEmpty(ExceptionCodeProd.PORTFOLIO_UPDATE_ERROR_INVALID_MATCH_GALLERY);
        }
        Portfolio lastPortfolio = lastPortfolioOptional.get();
        lastPortfolio.delete();
        return new ResponseEmpty(ExceptionCodeProd.PORTFOLIO_DELETE_OK);
    }
}
