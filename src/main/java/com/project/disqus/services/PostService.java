package com.project.disqus.services;

import com.project.disqus.entities.Post;
import com.project.disqus.entities.User;
import com.project.disqus.repos.PostRepository;
import com.project.disqus.repos.UserRepository;
import com.project.disqus.requests.PostCreateRequest;
import com.project.disqus.requests.PostUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> getAllPostsById(Optional<Long> userId) {
        if (userId.isPresent()) {
            return postRepository.findByUserId(userId);
        } else return null;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user = userService.getOneUser(newPostRequest.getUserId());
        if (user == null) {
            return null;
        } else {
            Post toSave = new Post();
            toSave.setId(newPostRequest.getId());
            toSave.setText(newPostRequest.getText());
            toSave.setTitle(newPostRequest.getTitle());
            toSave.setUser(user);
            return postRepository.save(toSave);
        }
    }

    public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Post toUpdate = post.get();
            toUpdate.setText(updatePost.getText());
            toUpdate.setTitle(updatePost.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        } else return null;
    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }

}
