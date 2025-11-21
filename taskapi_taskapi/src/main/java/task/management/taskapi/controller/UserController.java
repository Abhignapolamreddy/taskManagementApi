package task.management.taskapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import task.management.taskapi.dto.UserDto;
import task.management.taskapi.entity.Task;
import task.management.taskapi.entity.User;
import task.management.taskapi.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


@Autowired
private UserService userService;

// Create a new user
@PostMapping
public ResponseEntity<Object> createUser(@Valid @RequestBody UserDto userDto) {
	 Object createdUser = userService.createUser(userDto);
     return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
}

// Get all users
@GetMapping
public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.getAllUsers();
    return new ResponseEntity<>(users, HttpStatus.OK);
}

// Get all tasks for a user
@GetMapping("/{userId}/tasks")
public ResponseEntity<List<Task>> getTasksForUser(@PathVariable Long userId) {
    List<Task> tasks = userService.getTasksForUser(userId);
    return new ResponseEntity<>(tasks, HttpStatus.OK);
}


}

