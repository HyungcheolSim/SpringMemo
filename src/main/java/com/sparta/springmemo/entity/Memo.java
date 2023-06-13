package com.sparta.springmemo.entity;

import com.sparta.springmemo.dto.MemoRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Memo {
    private Long id;
    private String username;
    private String contents;

    public Memo(MemoRequestDTO requestDto) {
        this.username=requestDto.getUsername();
        this.contents=requestDto.getContents();
    }
}
