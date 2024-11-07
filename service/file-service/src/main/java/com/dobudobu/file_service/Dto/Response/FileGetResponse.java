package com.dobudobu.file_service.Dto.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileGetResponse {

    private String publicId;

    private String fileUrl;

    private String fileType;

}
