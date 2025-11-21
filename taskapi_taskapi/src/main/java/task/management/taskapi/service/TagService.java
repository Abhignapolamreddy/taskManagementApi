package task.management.taskapi.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import task.management.taskapi.dto.TagDto;
import task.management.taskapi.dto.TaskResponseDto;
import task.management.taskapi.entity.Tag;
import task.management.taskapi.entity.Task;

public interface TagService {
	
	Object createTag(TagDto tag);
	// Get all tags 
	Object getAllTags(); 
	// Assign a tag to a task
	TaskResponseDto assignTagToTask(Long taskId, Long tagId) throws NotFoundException; 
	// Remove a tag from a task 
	Task removeTagFromTask(Long taskId, Long tagId);

}
