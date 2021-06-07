package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*") /* bad version */

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/task")
@RequiredArgsConstructor
public class TaskController {
    private final DbService dbService;
    private final TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(
                dbService.getTask(taskId).orElseThrow(TaskNotFoundException::new)
        );
    }

    @GetMapping(value = "getTasks")
    public List<TaskDto> getTasks() {
        List<Task> tasks = dbService.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @GetMapping(value = "getTasks/{taskId}")
    public List<TaskDto> getTaskById(@PathVariable Long taskId) throws TaskNotFoundException  {
        if (!dbService.getTaskById(taskId).isEmpty())
            return taskMapper.mapToTaskDtoList(dbService.getTaskById(taskId));
        else
            throw new TaskNotFoundException();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask/taskId={taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        dbService.deleteTaskById(taskId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        Task savedTask = dbService.saveTask(task);
        return taskMapper.mapToTaskDto(savedTask);
    }

    @PostMapping(value = "createTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        dbService.saveTask(task);
    }
}
