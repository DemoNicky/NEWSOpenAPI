package com.dobudobu.article_management_service.Client;

import com.dobudobu.article_management_service.Dto.Response.FileResponse;
import com.dobudobu.article_management_service.Dto.Response.ResponseHandling;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "file-service", path = "/api/v1/file")
public interface FileFeignClient {

    @PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseHandling<FileResponse> postFile(@RequestPart(value = "file")MultipartFile file);

    @GetMapping(value = "/get-file")
    public ResponseHandling<FileResponse> getFile(@RequestParam(value = "publicId")String publicId);

}
