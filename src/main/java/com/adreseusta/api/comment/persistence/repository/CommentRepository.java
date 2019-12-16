package com.adreseusta.api.comment.persistence.repository;

import com.adreseusta.api.comment.persistence.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM comment o ORDER BY rand() limit ?1", nativeQuery = true)
    List<Comment> getRandomComment(int count);
}
