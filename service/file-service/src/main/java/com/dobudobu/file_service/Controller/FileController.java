package com.dobudobu.file_service.Controller;

import com.dobudobu.file_service.Dto.Response.FileGetResponse;
import com.dobudobu.file_service.Dto.Response.FilePostResponse;
import com.dobudobu.file_service.Dto.Response.ResponseHandling;
import com.dobudobu.file_service.Exception.ServiceCustomException.CustomFileNotValid;
import com.dobudobu.file_service.Service.FileService;
import com.dobudobu.file_service.Validation.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private FileValidator fileValidator;

    @PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseHandling<FilePostResponse>> uploadImage(@RequestPart(value = "file", required = true) MultipartFile file){
        if (file == null || file.isEmpty()) {
            throw new CustomFileNotValid("file cannot be null or empty");
        }
        if (!fileValidator.isValidFileExtension(file)){
            throw new CustomFileNotValid("file not valid, file extension not allowed");
        }
        if (!fileValidator.isValidMimeType(file)){
            throw new CustomFileNotValid("file not valid, file MIME type not allowed");
        }
        ResponseHandling<FilePostResponse> responseHandling = fileService.uploadService(file);
        return ResponseEntity.status(HttpStatus.OK).body(responseHandling);
    }

    @GetMapping(value = "/get-file")
    public ResponseEntity<ResponseHandling<FileGetResponse>> getFile(@RequestParam(value = "file", required = true) String publicId){
        if (publicId == null || publicId.isEmpty()) {
            throw new CustomFileNotValid("publicId cannot be null or empty");
        }
        ResponseHandling<FileGetResponse> responseHandling = fileService.getFIle(publicId);
        return ResponseEntity.status(HttpStatus.OK).body(responseHandling);
    }

}
