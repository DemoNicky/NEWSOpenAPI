package com.dobudobu.article_management_service.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@Table(name = "tb_comment_reply")
public class CommentReply {

    @Id
    @GenericGenerator(strategy = "uuid2", name = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column(length = 300)
    private String replyCommentContent;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date replyCommentPost;

    @Column(length = 10)
    private Long replyCommentLike;

    @Column(length = 10)
    private Long dissLike;

    private String userId;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

}
