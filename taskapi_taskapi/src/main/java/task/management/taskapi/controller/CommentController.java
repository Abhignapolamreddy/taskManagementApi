package task.management.taskapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import task.management.taskapi.dto.CommentDto;
import task.management.taskapi.dto.CommentResponseDto;
import task.management.taskapi.entity.Comment;
import task.management.taskapi.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/task/{taskId}/comments")
public class CommentController {

@Autowired
private CommentService commentService;

// Add a comment to a task
@PostMapping
public ResponseEntity<CommentResponseDto> addComment(@PathVariable Long taskId, @RequestBody CommentDto commentDto) {
	CommentResponseDto createdComment = commentService.addCommentToTask(taskId, commentDto);
    return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
}

// Get all comments for a task
@GetMapping
public ResponseEntity<List<Comment>> getComments(@PathVariable Long taskId) {
    List<Comment> comments = commentService.getCommentsByTask(taskId);
    return new ResponseEntity<>(comments, HttpStatus.OK);
}

}
