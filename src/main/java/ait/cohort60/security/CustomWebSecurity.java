package ait.cohort60.security;

import ait.cohort60.post.dao.PostRepository;
import ait.cohort60.post.model.Post;
import ait.cohort60.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomWebSecurity {
    private final PostService postService;
    private final PostRepository postRepository;

    public boolean checkPostAuthor(String postId, String username) {
        try {
            Long id = Long.parseLong(postId);
            Post post = postRepository.findById(id).orElse(null);
            return post != null && post.getAuthor().equalsIgnoreCase(username);
        } catch (NumberFormatException e) {
            return false;
        }

    }
}
