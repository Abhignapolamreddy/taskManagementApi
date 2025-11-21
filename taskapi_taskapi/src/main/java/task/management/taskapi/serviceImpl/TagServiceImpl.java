package task.management.taskapi.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import task.management.taskapi.dto.TagDto;
import task.management.taskapi.dto.TaskResponseDto;
import task.management.taskapi.entity.Tag;
import task.management.taskapi.entity.Task;
import task.management.taskapi.repository.TagRepository;
import task.management.taskapi.repository.TaskRepository;
import task.management.taskapi.service.TagService;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Object createTag(TagDto tag) {
		Tag t= new Tag();
		t.setTitle(tag.getName());
		return tagRepository.save(t);
	}

	@Override
	public Object getAllTags() {
		return tagRepository.findAll();
	}
	   public Task getById(Long id) throws NotFoundException {
	     
				return taskRepository.findById(id).orElseThrow(() -> new NotFoundException());
			
	    }
	   @Transactional
	   @Override
	   public TaskResponseDto assignTagToTask(Long taskId, Long tagId) {

	       Task task = taskRepository.findById(taskId)
	               .orElseThrow(() -> new RuntimeException("Task not found"));

	       Tag tag = tagRepository.findById(tagId)
	               .orElseThrow(() -> new RuntimeException("Tag not found"));

	       task.getTags().add(tag);

	       taskRepository.save(task);

	       // Convert to response DTO
	       List<TagDto> tagDtos = task.getTags().stream()
	               .map(t -> new TagDto(t.getId(), t.getTitle()))
	               .toList();

	       return new TaskResponseDto(
	               task.getId(),
	               task.getTitle(),
	               tagDtos
	       );
	   }


	   @Transactional
	    @Override
	    public Task removeTagFromTask(Long taskId, Long tagId) {
	        Task task = taskRepository.findById(taskId)
	                .orElseThrow(() -> new RuntimeException("Task not found"));

	        Tag tag = tagRepository.findById(tagId)
	                .orElseThrow(() -> new RuntimeException("Tag not found"));

	        task.getTags().remove(tag);
	        tag.getTasks().remove(task);

	        return taskRepository.save(task);
	    }
}
