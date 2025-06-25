package ait.cohort60.post.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime dateCreated;
    @Singular
    private Set<String> tags;
    private Integer likes;
    @Singular
    private List<CommentDto> comments;
}
