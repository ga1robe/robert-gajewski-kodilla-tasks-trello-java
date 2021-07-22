package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TaskDtoTestSuite {

    @Test
    void getTitle() {
        /* Given */
        TaskDto taskDto = new TaskDto(1L, "test title", "test content");
        /* When */
        String title = taskDto.getTitle();
        /* Then */
        assertThat(title).isEqualTo("test title");
    }

    @Test
    void getContent() {
        /* Given */
        TaskDto taskDto = new TaskDto(1L, "test title", "test content");
        /* When */
        String content = taskDto.getContent();
        /* Then */
        assertThat(content).isEqualTo("test content");
    }
}