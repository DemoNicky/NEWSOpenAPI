package com.dobudobu.article_management_service.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleCreateRequest {

    @NotBlank(message = "Article title must not be blank")
    @Size(max = 100, message = "Article title must be less than 100 characters")
    private String articleTitle;

    @NotBlank(message = "Content must not be blank")
    private String content;

    @NotBlank(message = "Category ID must not be blank")
    private String categoryId;
}
