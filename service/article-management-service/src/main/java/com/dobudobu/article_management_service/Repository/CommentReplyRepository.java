package com.dobudobu.article_management_service.Repository;

import com.dobudobu.article_management_service.Entity.CommentReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReplyRepository extends JpaRepository<CommentReply, String> {

}
