package ait.cohort60.post.dao;

import ait.cohort60.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public interface PostRepository extends JpaRepository<Post, Long> {
    Stream<Post> findPostByAuthorIgnoreCase(String author);

    Stream<Post> findDistinctByTagsNameInIgnoreCase(List<String> tags);

    Stream<Post> findByDateCreatedBetween(LocalDateTime from, LocalDateTime to);
}
