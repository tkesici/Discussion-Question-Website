package com.project.disqus.controllers;

import com.project.disqus.entities.Comment;
import com.project.disqus.requests.CommentCreateRequest;
import com.project.disqus.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId,
                                        @RequestParam Optional<Long> postId) {
        return commentService.getAllCommentsWithParam(userId,postId);
    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest request) {
        return commentService.createOneComment(request);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId) {
        return commentService.getOneCommentById(commentId);
    }

}
