package com.dobudobu.article_management_service.Dto.Response;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class GetListArticleResponse {

    private String articleCode;

    private String articleTitle;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postedArticle;

    private Long readerShip;

    private FileResponse fileResponse;

}
