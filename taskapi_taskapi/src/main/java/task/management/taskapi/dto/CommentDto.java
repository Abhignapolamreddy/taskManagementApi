package task.management.taskapi.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentDto {
	 private String text;
	    private LocalDateTime createdAt;

	    private Long taskId;
	    private Long userId;
}
