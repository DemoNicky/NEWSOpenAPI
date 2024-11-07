package com.dobudobu.file_service.Service;

import com.dobudobu.file_service.Dto.Response.FileGetResponse;
import com.dobudobu.file_service.Dto.Response.FilePostResponse;
import com.dobudobu.file_service.Dto.Response.ResponseHandling;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ResponseHandling<FilePostResponse> uploadService(MultipartFile file);

    ResponseHandling<FileGetResponse> getFIle(String publicId);
}
