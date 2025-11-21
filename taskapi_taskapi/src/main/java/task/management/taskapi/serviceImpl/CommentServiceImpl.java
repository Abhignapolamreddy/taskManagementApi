package task.management.taskapi.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import task.management.taskapi.dto.CommentDto;
import task.management.taskapi.dto.CommentResponseDto;
import task.management.taskapi.dto.SimpleUserDto;
import task.management.taskapi.entity.Comment;
import task.management.taskapi.entity.Task;
import task.management.taskapi.repository.CommentRepository;
import task.management.taskapi.repository.TaskRepository;
import task.management.taskapi.repository.UserRepository;
import task.management.taskapi.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public CommentResponseDto addCommentToTask(Long taskId, CommentDto commentDto) {
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
		Comment comment = new Comment();
		comment.setText(commentDto.getText());
		comment.setCreatedAt(LocalDateTime.now());
		comment.setTask(task);
		if (commentDto.getUserId() != null) {
			userRepository.findById(commentDto.getUserId()).ifPresent(comment::setUser);
		}
		
		 Comment savedComment= commentRepository.save(comment);
		  SimpleUserDto userDto = new SimpleUserDto(
		            savedComment.getUser().getId(),
		            savedComment.getUser().getName()
		    );

		    return new CommentResponseDto(
		            savedComment.getId(),
		            savedComment.getText(),
		            savedComment.getCreatedAt(),
		            userDto
		    );
	}

	@Override
	public List<Comment> getCommentsByTask(Long taskId) {
		Task task = taskRepository.findById(taskId) .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId)); 
		List<Comment> comments = commentRepository.findByTask(task);
		return comments;
	}

}
