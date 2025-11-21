package task.management.taskapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import task.management.taskapi.dto.TagDto;
import task.management.taskapi.dto.TaskResponseDto;
import task.management.taskapi.entity.Tag;
import task.management.taskapi.entity.Task;
import task.management.taskapi.service.TagService;

@RestController
@RequestMapping("/api/tag")
public class TagController {

	@Autowired
	private TagService tagService;

// Create a new tag
	@PostMapping
	public ResponseEntity<Object> createTag(@RequestBody TagDto tag) {
		Object createdTag = tagService.createTag(tag);
		return new ResponseEntity<>(createdTag, HttpStatus.CREATED);
	}

// Get all tags
	@GetMapping
	public ResponseEntity<Object> getAllTags() {
		Object tags = tagService.getAllTags();
		return new ResponseEntity<>(tags, HttpStatus.OK);
	}

	@PostMapping("/tasks/{taskId}/tags/{tagId}")
	public ResponseEntity<TaskResponseDto> assignTag(
	        @PathVariable Long taskId,
	        @PathVariable Long tagId) throws NotFoundException {

	    return ResponseEntity.ok(tagService.assignTagToTask(taskId, tagId));
	}
	    @DeleteMapping("/tasks/{taskId}/tags/{tagId}")
	    public ResponseEntity<Task> removeTag(@PathVariable Long taskId, @PathVariable Long tagId) {
	        return ResponseEntity.ok(tagService.removeTagFromTask(taskId, tagId));
	    }

}
