package com.the.dark.side.crew.fejsbuk.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private long id;

    private String message;

    private LocalDateTime createdAt;

    private long userId;

    private long postId;
}
