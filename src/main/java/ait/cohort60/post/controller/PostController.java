package ait.cohort60.post.controller;

import ait.cohort60.post.dto.NewCommentDto;
import ait.cohort60.post.dto.NewPostDto;
import ait.cohort60.post.dto.PostDto;
import ait.cohort60.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class PostController {
    private final PostService postService;

    @PostMapping("/post/{user}")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto addNewPost(@PathVariable String author, @RequestBody NewPostDto newPostDto) {
        return postService.addNewPost(author, newPostDto);
    }

    @GetMapping("/post/{postId}")
    public PostDto findPostById(@PathVariable Long id) {
        return postService.findPostById(id);
    }

    @PatchMapping("/post/{postId}/like")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addLike(@PathVariable Long id) {
        postService.addLike(id);
    }

    @PatchMapping("/post/{postId}")
    public PostDto updatePost(@PathVariable Long id, @RequestBody NewPostDto newPostDto) {
        return postService.updatePost(id, newPostDto);
    }

    @DeleteMapping("/post/{postId}")
    public PostDto deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

    @PatchMapping("/post/{postId}/comment/{commenter}")
    public PostDto addComment(@PathVariable Long id,
                              @PathVariable String author,
                              @RequestBody NewCommentDto newCommentDto) {
        return postService.addComment(id, author, newCommentDto);
    }

    @GetMapping("/posts/author/{user}")
    public Iterable<PostDto> findPostsByAuthor(@PathVariable String author) {
        return postService.findPostsByAuthor(author);
    }

    @GetMapping("/posts/tags")
    public Iterable<PostDto> findPostsByTags(@RequestParam("values") List<String> tags) {
        return postService.findPostsByTags(tags);
    }

    @GetMapping("/posts/period")
    public Iterable<PostDto> findPostsByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {
        return postService.findPostsByPeriod(dateFrom, dateTo);
    }
}
