package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {

    @Autowired
    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTaskById(final Long id) {
        return taskRepository.findAllById(id);
    }

    public Optional<Task> getTask(final Long taskId) {
        return taskRepository.findById(taskId);
    }

    public Task saveTask(final Task task) {
        return taskRepository.save(task);
    }

    public void deleteTaskById(final Long id){
        taskRepository.deleteById(id);
    }
}
