package com.dobudobu.article_management_service.Controller;

import com.dobudobu.article_management_service.Dto.Request.ArticleCreateRequest;
import com.dobudobu.article_management_service.Dto.Response.GetListArticleResponse;
import com.dobudobu.article_management_service.Dto.Response.ResponseHandling;
import com.dobudobu.article_management_service.Service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(
        name = "Rest APIs article",
        description = "Rest Article in service article management service for manage article"
)
@RestController
@RequestMapping("/api/v1/article")
@Validated
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Operation(
            summary = "Crate Article",
            description = "Method for create article where file should be image or video"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Article Created Sucessfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "user get wrong input"
            )
    })
    @PostMapping(
            value = "/create-article",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ResponseHandling<?>> createArticle(@RequestHeader("userCode") String userCode,
                                                             @Valid @RequestPart("article")ArticleCreateRequest articleCreateRequest,
                                                             @RequestPart("file")MultipartFile file){
        ResponseHandling<?> responseHandling = articleService.createArticle(userCode, articleCreateRequest, file);
        return ResponseEntity.status(HttpStatus.OK).body(responseHandling);
    }

    @GetMapping(value = "/helo")
    public String helo(){
        return "hello";
    }

    @GetMapping(value = "/get-article/{page}")
    public ResponseEntity<ResponseHandling<List<GetListArticleResponse>>> getArticle(@PathVariable("page")int page){
        ResponseHandling<List<GetListArticleResponse>> responseHandling = articleService.getArticle(page);
        return ResponseEntity.status(HttpStatus.OK).body(responseHandling);
    }

}
