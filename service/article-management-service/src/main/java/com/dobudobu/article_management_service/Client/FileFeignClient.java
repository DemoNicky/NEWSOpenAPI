package com.dobudobu.article_management_service.Client;

import com.dobudobu.article_management_service.Dto.Response.FilePostResponse;
import com.dobudobu.article_management_service.Dto.Response.ResponseHandling;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "file-service", path = "/api/v1/file")
public interface FileFeignClient {

    @PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseHandling<FilePostResponse> postFile(@RequestPart(value = "file")MultipartFile file);

}
