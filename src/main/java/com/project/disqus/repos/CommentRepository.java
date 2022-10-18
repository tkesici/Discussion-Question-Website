package com.project.disqus.repos;


import com.project.disqus.entities.Comment;
import com.project.disqus.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);

    List<Comment> findByPostIdAndPostId(Long userId, Long postId);

    List<Comment> findByUserId(Long userId);

}
