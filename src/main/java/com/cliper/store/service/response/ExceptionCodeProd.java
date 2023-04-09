package com.cliper.store.service.response;

import lombok.Getter;

import java.util.Arrays;

import static com.cliper.store.service.response.ResponseMessage.*;

@Getter
public enum ExceptionCodeProd {
    // 갤러리
    GALLERY_GET_OK(HttpStatus.SUCCESS, "A000", GALLERY_GET_MESSAGE),
    GALLERY_UPDATE_ERROR_INVALID_MATCH_GALLERY(HttpStatus.INVALID_ACCESS, "A001", GALLERY_INVALID_GET_MESSAGE),
    GALLERY_CREATE_OK(HttpStatus.SUCCESS, "B000", GALLERY_CREATE_MESSAGE),
    GALLERY_UPDATE_OK(HttpStatus.SUCCESS, "C000", GALLERY_UPDATE_MESSAGE),
    GALLERY_DELETE_OK(HttpStatus.SUCCESS, "D000", GALLERY_DELETE_MESSAGE),

    // 포트폴리오
    PORTFOLIO_GET_OK(HttpStatus.SUCCESS, "A000", PORTFOLIO_GET_MESSAGE),
    PORTFOLIO_UPDATE_ERROR_INVALID_MATCH_GALLERY(HttpStatus.INVALID_ACCESS, "A001", PORTFOLIO_INVALID_GET_MESSAGE),
    PORTFOLIO_CREATE_OK(HttpStatus.SUCCESS, "B000", PORTFOLIO_CREATE_MESSAGE),
    PORTFOLIO_UPDATE_OK(HttpStatus.SUCCESS, "C000", PORTFOLIO_UPDATE_MESSAGE),
    PORTFOLIO_DELETE_OK(HttpStatus.SUCCESS, "D000", PORTFOLIO_DELETE_MESSAGE),

    // 공용
    CREATE_ERROR_INVALID_EXTENSION(HttpStatus.INVALID_ACCESS, "B001", INVALID_EXTENSION_MESSAGE),
    CREATE_ERROR_INVALID_IMAGE(HttpStatus.INVALID_ACCESS, "B002", INVALID_IMAGE_MESSAGE),
    CREATE_ERROR_INVALID_SAVE(HttpStatus.INVALID_ACCESS, "B003", INVALID_FILE_SAVE_MESSAGE),
    EMPTY(HttpStatus.EMPTY, "", ResponseMessage.EMPTY);

    private final HttpStatus status;
    private final String code;
    private final ResponseMessage message;

    ExceptionCodeProd(HttpStatus status, String code, ResponseMessage message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public static ExceptionCodeProd findByResponseMessage(ResponseMessage responseMessage) {
        return Arrays.stream(values())
                .filter(exceptionCode -> exceptionCode.message == responseMessage)
                .findFirst()
                .orElse(EMPTY);
    }

    public String getMessage() {
        return message.getResponseMessage();
    }
}
