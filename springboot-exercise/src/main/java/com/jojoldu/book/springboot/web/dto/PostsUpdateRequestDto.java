package com.jojoldu.book.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class PostsUpdateRequestDto {
    private String content;
    private Long emotion;

    @Builder
    public PostsUpdateRequestDto(String content, Long emotion) {
        this.content = content;
        this.emotion= emotion;
    }
}
