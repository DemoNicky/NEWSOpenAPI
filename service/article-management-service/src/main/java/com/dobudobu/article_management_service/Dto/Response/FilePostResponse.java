package com.dobudobu.article_management_service.Dto.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FilePostResponse {

    private String publicId;

    private String fileUrl;

    private String fileType;

}