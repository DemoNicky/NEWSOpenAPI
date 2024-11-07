package com.dobudobu.file_service.Service.Implement;

import com.dobudobu.file_service.Dto.Response.FileGetResponse;
import com.dobudobu.file_service.Dto.Response.FilePostResponse;
import com.dobudobu.file_service.Dto.Response.ResponseHandling;
import com.dobudobu.file_service.Dto.Response.UploadFileResult;
import com.dobudobu.file_service.Entity.File;
import com.dobudobu.file_service.Exception.ServiceCustomException.CustomFailedUploadFile;
import com.dobudobu.file_service.Exception.ServiceCustomException.CustomNotFoundException;
import com.dobudobu.file_service.Repository.FileRepository;
import com.dobudobu.file_service.Service.FileService;
import com.dobudobu.file_service.Util.UploadUtils.UploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class FileServiceImplement implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UploadUtils uploadUtils;

    @Override
    public ResponseHandling<FilePostResponse> uploadService(MultipartFile file) {
        ResponseHandling<FilePostResponse> responseHandling = new ResponseHandling<>();
        UploadFileResult uploadFileResult;
        try {
            uploadFileResult = uploadUtils.uploadImageResult(file);
        }catch (IOException e){
            log.error(e.toString());
            throw new CustomFailedUploadFile("file error while upload");
        }

        File fileBld = new File();
        fileBld.setPublicId(uploadFileResult.getPublicId());
        fileBld.setFileUrl(uploadFileResult.getFileUrl());
        fileBld.setFileType(uploadFileResult.getFileType());

        fileRepository.save(fileBld);

        FilePostResponse filePostResponse = FilePostResponse.builder()
                .publicId(fileBld.getPublicId())
                .fileUrl(fileBld.getFileUrl())
                .fileType(fileBld.getFileType())
                .build();

        responseHandling.setData(filePostResponse);
        responseHandling.setStatus("success");
        responseHandling.setMessage("success uploading image");
        responseHandling.setHttpStatus(HttpStatus.OK);
        responseHandling.setErrors(false);

        return responseHandling;
    }

    @Override
    public ResponseHandling<FileGetResponse> getFIle(String publicId) {
        ResponseHandling<FileGetResponse> responseHandling = new ResponseHandling<>();
        Optional<File> file = fileRepository.findByPublicId(publicId);
        if (file.isEmpty()){
            throw new CustomNotFoundException("file with id " + publicId + " not found");
        }
        FileGetResponse fileGetResponse = new FileGetResponse();
        fileGetResponse.setPublicId(file.get().getPublicId());
        fileGetResponse.setFileUrl(file.get().getFileUrl());
        fileGetResponse.setFileType(file.get().getFileType());

        responseHandling.setData(fileGetResponse);
        responseHandling.setStatus("success");
        responseHandling.setMessage("success get file");
        responseHandling.setHttpStatus(HttpStatus.OK);
        responseHandling.setErrors(false);
        return responseHandling;
    }
}
