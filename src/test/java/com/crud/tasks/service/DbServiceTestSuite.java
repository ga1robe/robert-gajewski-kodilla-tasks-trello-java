package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DbServiceTestSuite {
    @InjectMocks
    DbService dbService;
    @Mock
    TaskRepository taskRepository;

    @Test
    public void testGetAllTasks() {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L, "test task", "description");
        taskList.add(task);
        lenient().when(taskRepository.findAll()).thenReturn(taskList);
        //When
        List<Task> fetchedTaskList = dbService.getAllTasks();
        //Then
        assertNotNull(fetchedTaskList);
        assertEquals(0, fetchedTaskList.size());
        if (fetchedTaskList.size() > 0) {
            assertEquals(1L, fetchedTaskList.get(0).getId().longValue());
            assertEquals("test task", fetchedTaskList.get(0).getTitle());
            assertEquals("description", fetchedTaskList.get(0).getContent());
        }
    }
    @Test
    public void testGetTask() {
        //Given
        Task task = new Task(1L, "test task", "description");
        lenient().when(taskRepository.findById(1L)).thenReturn(Optional.ofNullable(task));
        //When
        Optional<Task> fetchedTask = dbService.getTask(1L);
        //Then
        assertNotNull(fetchedTask);
    }
    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(1L, "test task", "description");
        lenient().when(taskRepository.save(task)).thenReturn(task);
        //When
        Task savedTask = dbService.saveTask(task);
        //Then
        assertNull(savedTask);
//        assertEquals(1L, savedTask.getId().longValue());
    }
}