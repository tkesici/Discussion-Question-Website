package com.project.disqus.services;

import com.project.disqus.entities.Comment;
import com.project.disqus.entities.Post;
import com.project.disqus.repos.CommentRepository;
import com.project.disqus.repos.PostRepository;
import com.project.disqus.repos.UserRepository;
import com.project.disqus.requests.CommentCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getAllCommentsByPostId(Long postId) {

        return commentRepository.findByPostId(postId);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByPostIdAndPostId(userId.get(),postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        } else
            return commentRepository.findAll();
    }

    public Comment createOneComment(CommentCreateRequest request) {

    }
}
