package com.cliper.store.service.response;

import java.util.Arrays;

public enum ResponseMessage {
    GALLERY_GET_MESSAGE("게시물 확인에 성공했습니다."),
    GALLERY_CREATE_MESSAGE("게시물 작성에 성공했습니다."),
    GALLERY_INVALID_EXTENSION_MESSAGE("잘못된 확장자를 입력했습니다."),
    GALLERY_INVALID_IMAGE_MESSAGE("이미지를 입력해주세요."),
    GALLERY_INVALID_FILE_SAVE_MESSAGE("파일을 저장할 수 없습니다."),
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
