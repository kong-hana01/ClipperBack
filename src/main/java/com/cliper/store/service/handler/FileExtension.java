package com.cliper.store.service.handler;

import java.util.Arrays;

public enum FileExtension {
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png");

    private static final String INVALID_EXTENSION_MESSAGE = "잘못된 확장자를 입력했습니다.";
    private final String extension;

    FileExtension(String extension) {
        this.extension = extension;
    }

    static FileExtension findMatchExtension(String extension) {
        return Arrays.stream(values())
                .filter(x -> x.extension.equalsIgnoreCase(extension))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_EXTENSION_MESSAGE));
    }

    String getExtension() {
        return extension;
    }
}
