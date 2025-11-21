package task.management.taskapi.service;

import java.util.List;

import task.management.taskapi.dto.CommentDto;
import task.management.taskapi.dto.CommentResponseDto;
import task.management.taskapi.entity.Comment;

public interface CommentService {

	// Add a comment to a task
	CommentResponseDto addCommentToTask(Long taskId, CommentDto commentDto);

	// Get all comments for a task
	List<Comment> getCommentsByTask(Long taskId);

}
