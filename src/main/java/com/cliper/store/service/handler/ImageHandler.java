package com.cliper.store.service.handler;

import com.cliper.store.domain.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.cliper.store.utils.Paths.IMAGE_PATH;

public class ImageHandler {

    public static final String INVALID_IMAGE_MESSAGE = "이미지를 입력해주세요.";
    public static final String INVALID_FILE_SAVE_MESSAGE = "파일을 저장할 수 없습니다.";

    public List<Image> parseImageInfo(List<MultipartFile> multipartFiles) {
        ArrayList<Image> files = new ArrayList<>();
        if (multipartFiles.isEmpty()) {
            return files;
        }
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        File file = new File(IMAGE_PATH + currentDate);
        makeDirectory(file);
        addImages(multipartFiles, files, currentDate);
        return files;
    }

    private void makeDirectory(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private void addImages(List<MultipartFile> multipartFiles, ArrayList<Image> files, String currentDate) {
        String absolutePath = new File(IMAGE_PATH).getAbsolutePath() + "/";
        for (MultipartFile multipartFile : multipartFiles) {
            String imageName = extractImageName(multipartFile);
            Image image = Image.builder()
                    .fileName(currentDate + "/" + imageName)
                    .isThumbnail(false)
                    .build();
            files.add(image);
            saveImage(currentDate, absolutePath, multipartFile, imageName);
        }
        files.get(0).setThumbnail(true);
    }

    private String extractFileExtension(String contentType) {
        FileExtension matchExtension = FileExtension.findMatchExtension(contentType);
        return matchExtension.getExtension();
    }

    private static void saveImage(String currentDate, String absolutePath, MultipartFile multipartFile, String imageName) {
        try {
            multipartFile.transferTo(new File(absolutePath + currentDate + "/" + imageName));
        } catch (IOException exception) {
            throw new IllegalArgumentException(INVALID_FILE_SAVE_MESSAGE);
        }
    }

    private String extractImageName(MultipartFile multipartFile) {
        if (!multipartFile.isEmpty()) {
            String contentType = multipartFile.getContentType();
            String fileExtension = extractFileExtension(contentType);
            return UUID.randomUUID() + fileExtension;
        }
        throw new IllegalArgumentException(INVALID_IMAGE_MESSAGE);
    }
}


