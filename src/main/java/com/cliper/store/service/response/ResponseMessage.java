package com.cliper.store.service.response;

import java.util.Arrays;

public enum ResponseMessage {
    // 갤러리
    GALLERY_GET_MESSAGE("갤러리 확인에 성공했습니다."),
    GALLERY_CREATE_MESSAGE("갤러리 작성에 성공했습니다."),
    GALLERY_UPDATE_MESSAGE("갤러리 업데이트에 성공했습니다."),
    GALLERY_DELETE_MESSAGE("갤러리 삭제에 성공했습니다."),
    GALLERY_INVALID_GET_MESSAGE("갤러리를 찾을 수 없습니다."),

    // 포트폴리오
    PORTFOLIO_GET_MESSAGE("포트폴리오 확인에 성공했습니다."),
    PORTFOLIO_CREATE_MESSAGE("포트폴리오 작성에 성공했습니다."),
    PORTFOLIO_UPDATE_MESSAGE("포트폴리오 업데이트에 성공했습니다."),
    PORTFOLIO_DELETE_MESSAGE("포트폴리오 삭제에 성공했습니다."),
    PORTFOLIO_INVALID_GET_MESSAGE("포트폴리오를 찾을 수 없습니다."),

    // 공용
    INVALID_EXTENSION_MESSAGE("잘못된 확장자를 입력했습니다."),
    INVALID_IMAGE_MESSAGE("이미지를 입력해주세요."),
    INVALID_FILE_SAVE_MESSAGE("파일을 저장할 수 없습니다."),
    INVALID_DATABASE_ACCESS_MESSAGE("데이터베이스에 접근할 수 없습니다."),
    EMPTY("");

    final String responseMessage;

    ResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public static ResponseMessage findByMessage(String message) {
        return Arrays.stream(values())
                .filter(responseMessage -> responseMessage.responseMessage.equals(message))
                .findFirst()
                .orElse(EMPTY);
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
