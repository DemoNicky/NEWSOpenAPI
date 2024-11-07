package com.dobudobu.file_service.Dto.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UploadFileResult {

    private String fileUrl;

    private String publicId;

    private String fileType;

}
