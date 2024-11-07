package com.dobudobu.article_management_service.Repository;

import com.dobudobu.article_management_service.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String > {
}
