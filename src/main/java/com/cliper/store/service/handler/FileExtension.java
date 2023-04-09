package com.cliper.store.service.handler;

import java.util.Arrays;

import static com.cliper.store.service.response.ResponseMessage.INVALID_EXTENSION_MESSAGE;

public enum FileExtension {
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png");

    public static final String EXTENSION_DOT = ".";
    private final String extension;

    FileExtension(String extension) {
        this.extension = extension;
    }

    static FileExtension findMatchExtension(String extension) {
        return Arrays.stream(values())
                .filter(x -> x.extension.equalsIgnoreCase(extension))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_EXTENSION_MESSAGE.getResponseMessage()));
    }

    String getFullExtension() {
        return EXTENSION_DOT + extension;
    }

    String getExtension() {
        return extension;
    }
}
