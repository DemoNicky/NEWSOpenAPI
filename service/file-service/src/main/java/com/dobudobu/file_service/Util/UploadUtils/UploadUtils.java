package com.dobudobu.file_service.Util.UploadUtils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dobudobu.file_service.Dto.Response.UploadFileResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UploadUtils {

    private final Cloudinary cloudinary;

    private static final long MAX_FILE_SIZE_BYTES = 2L * 1024 * 1024 * 1024; // 2 GB

    public UploadFileResult uploadImageResult(MultipartFile file) throws IOException {
        String fileType = file.getContentType();

        if (file.getSize() > MAX_FILE_SIZE_BYTES){
            throw new IllegalArgumentException("File size exceeds the maximum allowed limit of 2 GB.");
        }

        Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String fileUrl = result.get("url").toString();
        String publicId = result.get("public_id").toString();

        UploadFileResult uploadImageResult = UploadFileResult.builder()
                .fileUrl(fileUrl)
                .publicId(publicId)
                .fileType(fileType)
                .build();
        return uploadImageResult;
    }

}
