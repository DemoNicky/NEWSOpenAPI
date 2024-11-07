package com.dobudobu.article_management_service.Service.Implement;

import com.dobudobu.article_management_service.Client.FileFeignClient;
import com.dobudobu.article_management_service.Dto.Request.ArticleCreateRequest;
import com.dobudobu.article_management_service.Dto.Response.ArticleCreateResponse;
import com.dobudobu.article_management_service.Dto.Response.FilePostResponse;
import com.dobudobu.article_management_service.Dto.Response.GetListArticleResponse;
import com.dobudobu.article_management_service.Dto.Response.ResponseHandling;
import com.dobudobu.article_management_service.Entity.Article;
import com.dobudobu.article_management_service.Exception.ServiceCustomException.CustomExceptionAlreadyExists;
import com.dobudobu.article_management_service.Exception.ServiceCustomException.CustomExceptionUploadFileError;
import com.dobudobu.article_management_service.Repository.ArticleRepository;
import com.dobudobu.article_management_service.Service.ArticleService;
import com.dobudobu.article_management_service.Util.GeneratedCode.GeneratedCodeUtil;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImplement implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private GeneratedCodeUtil generatedCodeUtil;

    @Autowired
    private FileFeignClient fileFeignClient;


    @Override
    public ResponseHandling<?> createArticle(ArticleCreateRequest articleCreateRequest, MultipartFile file) {
        ResponseHandling<ArticleCreateResponse> responseHandling = new ResponseHandling<>();

        Optional<Article> article = articleRepository.findArticleByArticleTitle(articleCreateRequest.getArticleTitle());
        if (!article.isEmpty()) {
            throw new CustomExceptionAlreadyExists("title of article is already exists");
        }

        ResponseHandling<FilePostResponse> fileResponse;
        try {
            fileResponse = fileFeignClient.postFile(file);
        } catch (FeignException.BadRequest e) {
            throw new CustomExceptionUploadFileError("Failed to post image due to a bad request: " + e.contentUTF8());
        } catch (FeignException e) {
            throw new CustomExceptionUploadFileError("Failed to post image due to an error: " + e.getMessage());
        }

        Article articleSet = new Article();
        articleSet.setArticleCode(generatedCodeUtil.generateCode(articleRepository::existsByArticleCode));
        articleSet.setArticleTitle(articleCreateRequest.getArticleTitle());
        articleSet.setPostedArticle(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        articleSet.setReaderShip(0L);
        articleSet.setArticleLike(0L);
        articleSet.setUserCode("1234");
        articleSet.setContent(articleCreateRequest.getContent());
        articleSet.setCategoryId("1");
        articleSet.setImageId(fileResponse.getData().getPublicId());
        articleSet.setAcc(false);

        articleRepository.save(articleSet);

        ArticleCreateResponse articleCreateResponse = new ArticleCreateResponse();
        articleCreateResponse.setArticleCode(articleSet.getArticleCode());
        articleCreateResponse.setArticleTitle(articleSet.getArticleTitle());
        articleCreateResponse.setPostedArticle(articleSet.getPostedArticle());
        articleCreateResponse.setReaderShip(articleSet.getReaderShip());
        articleCreateResponse.setLike(articleSet.getArticleLike());
        articleCreateResponse.setUserCode(articleSet.getUserCode());
        articleCreateResponse.setContent(articleSet.getContent());
        articleCreateResponse.setCategoryId(articleSet.getCategoryId());
        articleCreateResponse.setImageUrl(fileResponse.getData().getFileUrl());
        articleCreateResponse.setAcc(articleSet.getAcc());

        responseHandling.setData(articleCreateResponse);
        responseHandling.setStatus("success");
        responseHandling.setMessage("success get file");
        responseHandling.setHttpStatus(HttpStatus.OK);
        responseHandling.setErrors(false);

        return responseHandling;
    }

    @Override
    public ResponseHandling<List<GetListArticleResponse>> getArticle(int page) {

        return null;
    }


}