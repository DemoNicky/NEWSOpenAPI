package com.dobudobu.article_management_service.Dto.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ArticleCreateResponse {

    private String articleCode;

    private String articleTitle;

    private Date postedArticle;

    private Long readerShip;

    private Long like;

    private String userCode;

    private String content;

    private String categoryId;

    private String imageUrl;

    private Boolean acc;

}
