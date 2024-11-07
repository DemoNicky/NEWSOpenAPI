package com.dobudobu.article_management_service.Repository;

import com.dobudobu.article_management_service.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {

    Optional<Article> findArticleByArticleTitle(String articleTitle);

    boolean existsByArticleCode(String cutUid);
}
