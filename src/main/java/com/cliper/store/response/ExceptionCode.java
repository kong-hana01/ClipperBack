package com.cliper.store.response;

import java.util.Arrays;

import static com.cliper.store.response.HttpStatus.*;

public enum ExceptionCode {
    /**
     * 게시물 포스팅
     */
    BOARD_GET_OK(SUCCESS, "C000", "게시물"),
    BOARD_NOT_FOUND(NOT_FOUND_VALUE, "C001", "게시물을 찾을 수 없습니다."),
    BOARD_CREATE_OK(SUCCESS, "D000", "게시물 작성에 성공했습니다."),
    BOARD_CREATE_ERROR(SUCCESS, "D001", "게시물 작성에 실패했습니다."),
    BOARD_DELETE_OK(SUCCESS, "E000", "게시물을 삭제했습니다."),
    BOARD_DELETE_INVALID(INVALID_ACCESS, "E001", "게시물을 삭제할 수 있는 권한이 없습니다."),
    BOARD_UPDATE_OK(SUCCESS, "F000", "게시물 업데이트에 성공했습니다."),
    BOARD_UPDATE_INVALID(INVALID_ACCESS, "F001", "게시물을 변경할 수 있는 권한이 없습니다."),


    /**
     * 이미지
     */
    IMAGE_GET_OK(SUCCESS, "G000", "이미지"),
    IMAGE_NOT_FOUND(NOT_FOUND_VALUE, "G001", "이미지를 찾을 수 없습니다."),

    /**
     * 잘못된 ExceptionCode
     */
    EMPTY(null, "", "");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ExceptionCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ExceptionCode findExceptionCodeByCode(String code) {
        return Arrays.stream(ExceptionCode.values())
                .filter(x -> x.getCode().equals(code))
                .findFirst()
                .orElse(EMPTY);
    }
}
