package com.dobudobu.article_management_service.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "db_comment")
public class Comment {

    @Id
    @GenericGenerator(strategy = "uuid2", name = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column(length = 300)
    private String commentContent;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date commentPost;

    @Column(length = 10)
    private Long commentLike;

    @Column(length = 10)
    private Long dissLike;

    private String userCode;

    private String articleCode;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @OneToMany(mappedBy = "comment")
    private List<CommentReply> commentReply;

}
