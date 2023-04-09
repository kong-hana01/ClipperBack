package com.cliper.store.service.response;

import lombok.Getter;

import java.util.Arrays;

import static com.cliper.store.service.response.ResponseMessage.*;

@Getter
public enum ExceptionCodeProd {
    GALLERY_GET_OK(HttpStatus.SUCCESS, "A000", GALLERY_GET_MESSAGE),
    GALLERY_CREATE_OK(HttpStatus.SUCCESS, "B000", GALLERY_CREATE_MESSAGE),
    GALLERY_CREATE_ERROR_INVALID_EXTENSION(HttpStatus.INVALID_ACCESS, "B001", GALLERY_INVALID_EXTENSION_MESSAGE),
    GALLERY_CREATE_ERROR_INVALID_IMAGE(HttpStatus.INVALID_ACCESS, "B002", GALLERY_INVALID_IMAGE_MESSAGE),
    GALLERY_CREATE_ERROR_INVALID_SAVE(HttpStatus.INVALID_ACCESS, "B003", GALLERY_INVALID_FILE_SAVE_MESSAGE),
    GALLERY_UPDATE_OK(HttpStatus.SUCCESS, "C000", GALLERY_UPDATE_MESSAGE),
    GALLERY_CREATE_ERROR_INVALID_UPDATE(HttpStatus.INVALID_ACCESS, "C001", GALLERY_INVALID_GET_MESSAGE),

    EMPTY(null, "", ResponseMessage.EMPTY);

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
