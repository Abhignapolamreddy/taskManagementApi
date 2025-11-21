package task.management.taskapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import task.management.taskapi.constant.Status;
import task.management.taskapi.dto.TaskDto;
import task.management.taskapi.entity.Task;
import task.management.taskapi.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {


@Autowired
private TaskService taskService;

// Create a new task
@PostMapping
public ResponseEntity<Object> createTask(@Valid @RequestBody TaskDto taskDto) {
    Object createdTask = taskService.createTask(taskDto);
    return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
}

// Update a task
@PutMapping("/{id}")
public ResponseEntity<Object> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
    Object updatedTask = taskService.updateTask(id, taskDto);
    return new ResponseEntity<>(updatedTask, HttpStatus.OK);
}

// Get a task by ID
@GetMapping
public Page<Task> getAllTasks(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

    return taskService.getAllTasks(PageRequest.of(page, size));
}

@GetMapping("/status")
public Page<Task> getTasksByStatus(
        @RequestParam Status status,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

    return taskService.getTasksByStatus(status, PageRequest.of(page, size));
}

// Delete a task
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}


}

