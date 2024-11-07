package com.dobudobu.article_management_service.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "db_article")
public class Article {

    @Id
    @GenericGenerator(strategy = "uuid2", name = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column(length = 10, unique = true)
    private String articleCode;

    @Column(length = 30, unique = true)
    private String articleTitle;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postedArticle;

    @Column(length = 4)
    private Long readerShip;

    @Column(length = 10)
    private Long articleLike;

    private String userCode;

    @Column(length = 2500)
    private String content;

    private String categoryId;

    private String imageId;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<Comment> comment;

    private Boolean acc;

}
