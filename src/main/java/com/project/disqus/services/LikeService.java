package com.project.disqus.services;

import java.util.List;

import com.project.disqus.requests.LikeCreateRequest;
import org.springframework.stereotype.Service;

import com.project.disqus.entities.Like;
import com.project.disqus.entities.Post;
import com.project.disqus.entities.User;
import com.project.disqus.repos.LikeRepository;


@Service
public class LikeService {

    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService,
                       PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public Like getOneLikeById(Long LikeId) {
        return likeRepository.findById(LikeId).orElse(null);
    }

    public Like createOneLike(LikeCreateRequest request) {
        User user = userService.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        if (user != null && post != null) {
            Like likeToSave = new Like();
            likeToSave.setId(request.getId());
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            return likeRepository.save(likeToSave);
        } else
            return null;
    }

    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }


}
