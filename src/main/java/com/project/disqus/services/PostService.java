package com.project.disqus.services;

import com.project.disqus.entities.Post;
import com.project.disqus.entities.User;
import com.project.disqus.repos.PostRepository;
import com.project.disqus.repos.UserRepository;
import com.project.disqus.requests.PostCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserService userService;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> getAllPosts(Optional<Long> userId) {
        if (userId.isPresent()) {
            return postRepository.findByUserId(userId);
        } else return null;
    }

    public Post saveOnePost(Post newPost) {
        return postRepository.save(newPost);
    }
    public Post getOnePost(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user = userService.getOneUser(newPostRequest.getUserId());
        if(user == null) {
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
}
