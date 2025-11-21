package task.management.taskapi.dto;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Data;
import task.management.taskapi.constant.Status;

@Data
public class TaskDto {
	private String title;
    private String description;
    private Status status;

    private Long userId;
    private Set<Long> tagIds;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
