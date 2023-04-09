package com.cliper.store.service.response;

import java.util.Arrays;

public enum ExceptionCodeRef {
    /**
     * 게시물 포스팅
     */
    BOARD_GET_OK(HttpStatus.SUCCESS, "C000", "게시물"),
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND_VALUE, "C001", "게시물을 찾을 수 없습니다."),
    BOARD_CREATE_OK(HttpStatus.SUCCESS, "D000", "게시물 작성에 성공했습니다."),
    BOARD_CREATE_ERROR(HttpStatus.SUCCESS, "D001", "게시물 작성에 실패했습니다."),
    BOARD_DELETE_OK(HttpStatus.SUCCESS, "E000", "게시물을 삭제했습니다."),
    BOARD_DELETE_INVALID(HttpStatus.INVALID_ACCESS, "E001", "게시물을 삭제할 수 있는 권한이 없습니다."),
    BOARD_UPDATE_OK(HttpStatus.SUCCESS, "F000", "게시물 업데이트에 성공했습니다."),
    BOARD_UPDATE_INVALID(HttpStatus.INVALID_ACCESS, "F001", "게시물을 변경할 수 있는 권한이 없습니다."),


    /**
     * 이미지
     */
    IMAGE_GET_OK(HttpStatus.SUCCESS, "G000", "이미지"),
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND_VALUE, "G001", "이미지를 찾을 수 없습니다."),

    /**
     * 잘못된 ExceptionCode
     */
    EMPTY(null, "", "");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ExceptionCodeRef(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public static ExceptionCodeRef findExceptionCodeByCode(String code) {
        return Arrays.stream(ExceptionCodeRef.values())
                .filter(x -> x.getCode().equals(code))
                .findFirst()
                .orElse(EMPTY);
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
}
