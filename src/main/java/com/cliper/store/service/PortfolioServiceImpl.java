package com.cliper.store.service;

import com.cliper.store.domain.Image;
import com.cliper.store.domain.Portfolio;
import com.cliper.store.domain.PortfolioImage;
import com.cliper.store.dto.ClipperImageDto;
import com.cliper.store.dto.PortfolioDto;
import com.cliper.store.dto.PortfolioSaveDto;
import com.cliper.store.repository.PortfolioImageRepository;
import com.cliper.store.repository.PortfolioRepository;
import com.cliper.store.service.handler.ImageHandler;
import com.cliper.store.service.response.ExceptionCodeProd;
import com.cliper.store.service.response.Response;
import com.cliper.store.service.response.ResponseEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
        List<PortfolioDto> portfolioDtos = portfolios.stream()
                .map(Portfolio::toDto)
                .collect(Collectors.toList());
        return new Response(ExceptionCodeProd.PORTFOLIO_GET_OK, portfolioDtos);
    }

    @Override
    public Object savePortfolio(PortfolioSaveDto portfolioSaveDto, List<MultipartFile> files) {
        Portfolio portfolio = portfolioSaveDto.toEntity();
        List<Image> images = imageHandler.parseImageInfo(files);
        List<PortfolioImage> portfolioImages = saveImages(portfolio, images);

        setPortfolioImages(portfolio, portfolioImages);
        return new Response(ExceptionCodeProd.PORTFOLIO_CREATE_OK, portfolio.toDto());

    }

    private void setPortfolioImages(Portfolio portfolio, List<PortfolioImage> portfolioImages) {
        List<ClipperImageDto> clipperImageDtos = portfolioImages.stream()
                .map(PortfolioImage::toDto)
                .collect(Collectors.toList());
        PortfolioDto portfolioDto = portfolio.toDto();
        portfolioDto.setClipperImageDtos(clipperImageDtos);
    }

    private List<PortfolioImage> saveImages(Portfolio portfolio, List<Image> images) {
        List<PortfolioImage> portfolioImages = new ArrayList<>();
        for (Image image : images) {
            PortfolioImage portfolioImage = PortfolioImage.builder()
                    .portfolio(portfolio)
                    .image(image)
                    .build();
            portfolioImageRepository.save(portfolioImage);
            portfolioImages.add(portfolioImage);
        }
        return portfolioImages;
    }

    @Override
    public Object updatePortfolio(int portfolioId, PortfolioSaveDto portfolioSaveDto, List<MultipartFile> files) {
        Portfolio newPortfolio = portfolioSaveDto.toEntity();
        Optional<Portfolio> lastPortfolioOptional = portfolioRepository.findById(portfolioId);
        if (lastPortfolioOptional.isEmpty() || lastPortfolioOptional.get().getStatus() == 0) {
            return new ResponseEmpty(ExceptionCodeProd.PORTFOLIO_UPDATE_ERROR_INVALID_MATCH_GALLERY);
        }

        List<Image> images = imageHandler.parseImageInfo(files);
        List<PortfolioImage> portfolioImages = saveImages(newPortfolio, images);

        Portfolio portfolio = lastPortfolioOptional.get();
        deleteImages(portfolio);

        setPortfolio(portfolio, newPortfolio, portfolioImages);
        return new Response(ExceptionCodeProd.PORTFOLIO_UPDATE_OK, portfolio.toDto());

    }

    private void setPortfolio(Portfolio portfolio, Portfolio newPortfolio, List<PortfolioImage> portfolioImages) {
        portfolio.setTitle(newPortfolio.getTitle());
        portfolio.setContents(newPortfolio.getContents());
        portfolio.setAgency(newPortfolio.getAgency());
        portfolio.setIntroduction(newPortfolio.getIntroduction());
        portfolio.setDate(newPortfolio.getDate());

        setPortfolioImages(portfolio, portfolioImages);
    }

    private void deleteImages(Portfolio lastPortfolio) {
        List<Image> imageForDelete = lastPortfolio.getPortfolioImages()
                .stream()
                .map(PortfolioImage::getImage)
                .collect(Collectors.toList());
        imageHandler.deleteImages(imageForDelete);
    }

    @Override
    public Object deletePortfolio(int portfolioId) {
        Optional<Portfolio> lastPortfolioOptional = portfolioRepository.findById(portfolioId);
        if (lastPortfolioOptional.isEmpty() || lastPortfolioOptional.get().getStatus() == 0) {
            return new ResponseEmpty(ExceptionCodeProd.PORTFOLIO_UPDATE_ERROR_INVALID_MATCH_GALLERY);
        }
        Portfolio lastPortfolio = lastPortfolioOptional.get();
        lastPortfolio.delete();
        deleteImages(lastPortfolio);
        return new ResponseEmpty(ExceptionCodeProd.PORTFOLIO_DELETE_OK);
    }
}
