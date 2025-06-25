package ait.cohort60.post.service;

import ait.cohort60.post.dto.CommentDto;
import ait.cohort60.post.dto.NewCommentDto;
import ait.cohort60.post.dto.NewPostDto;
import ait.cohort60.post.dto.PostDto;

import java.time.LocalDate;
import java.util.List;

public interface PostService {
    PostDto addNewPost(String author, NewPostDto newPostDto);

    PostDto findPostById(Long id);

    void addLike(Long id);

    PostDto updatePost(Long id, NewPostDto newPostDto);

    PostDto deletePost(Long id);

    PostDto addComment(Long id, String author, NewCommentDto newCommentDto);

    Iterable<PostDto> findPostsByAuthor(String author);

    Iterable<PostDto> findPostsByTags(List<String> tags);

    Iterable<PostDto> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo);
}
