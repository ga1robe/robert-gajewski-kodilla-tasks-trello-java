package com.crud.tasks.domain;

import lombok.*;


//@NoArgsConstructor
//@AllArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter
public class TaskDto {
    private Long id;
    private String title;
    private String content;
}
