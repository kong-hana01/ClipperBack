package com.cliper.store.service.handler;

import java.util.Arrays;

import static com.cliper.store.service.response.ResponseMessage.GALLERY_INVALID_EXTENSION_MESSAGE;

public enum FileExtension {
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png");

    private final String extension;

    FileExtension(String extension) {
        this.extension = extension;
    }

    static FileExtension findMatchExtension(String extension) {
        return Arrays.stream(values())
                .filter(x -> x.extension.equalsIgnoreCase(extension))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(GALLERY_INVALID_EXTENSION_MESSAGE.getResponseMessage()));
    }

    String getExtension() {
        return extension;
    }
}
