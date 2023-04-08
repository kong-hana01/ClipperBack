package com.cliper.store.service.handler;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FileExtensionTest {

    @ParameterizedTest
    @CsvSource(value = {"jpg:JPG", "jpeg:JPEG", "png:PNG", "JPG:JPG", "JPEG:JPEG", "PNG:PNG"}, delimiter = ':')
    void 확장자_입력_테스트_성공(String extension, FileExtension expected) {
        assertThat(FileExtension.findMatchExtension(extension)).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"jpg1", "jpeg1", "png2", "asd"})
    void 확장자_입력_테스트_실패(String extension) {
        assertThatThrownBy(() -> FileExtension.findMatchExtension(extension))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 확장자를 입력했습니다.");
    }
}
