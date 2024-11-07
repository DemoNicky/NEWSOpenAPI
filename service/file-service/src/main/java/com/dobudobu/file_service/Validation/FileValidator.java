package com.dobudobu.file_service.Validation;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.util.Arrays;

@Component
public class FileValidator {

    private final String[] allowedExtensions = { "mp4", "jpg", "jpeg", "png", "gif", "bmp", "webp" };
    private final String[] allowedMimeTypes = { "image/jpeg", "image/jpg", "image/png", "image/gif", "image/bmp", "image/webp", "video/mp4" };

    public boolean isValidFileExtension(MultipartFile file) {
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
        return Arrays.asList(allowedExtensions).contains(fileExtension);
    }

    public boolean isValidMimeType(MultipartFile file) {
        String contentType = file.getContentType();
        return Arrays.asList(allowedMimeTypes).contains(contentType);
    }
}
