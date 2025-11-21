package task.management.taskapi.serviceImpl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import task.management.taskapi.constant.Status;
import task.management.taskapi.dto.TaskDto;
import task.management.taskapi.entity.Tag;
import task.management.taskapi.entity.Task;
import task.management.taskapi.repository.TagRepository;
import task.management.taskapi.repository.TaskRepository;
import task.management.taskapi.repository.UserRepository;
import task.management.taskapi.service.TaskService;

@Service

public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TagRepository tagRepository;
	@Override
	public Object createTask(TaskDto dto) {
		Task task = new Task(); task.setTitle(dto.getTitle()); task.setDescription(dto.getDescription()); 
		task.setStatus(dto.getStatus());
		 
		if (dto.getUserId() != null) { 
			userRepository.findById(dto.getUserId()).ifPresent(task::setUser);
			}  
		  Set<Tag> tags = new HashSet<>();
		  if (dto.getTagIds() != null) {
			for (Long tagId : dto.getTagIds()) { 
				tagRepository.findById(tagId).ifPresent(tags::add); 
				} 
			} 
		task.setTags(tags); 
		task.setCreatedAt(LocalDateTime.now()); 
		task.setUpdatedAt(LocalDateTime.now()); 
		Task saved = taskRepository.save(task);
		return saved;
	}

	@Override
	public Object updateTask(Long id, TaskDto dto) {
		Task existing = taskRepository.findById(id) .orElseThrow(() -> new RuntimeException("Task not found with id: " + id)); 
		existing.setTitle(dto.getTitle()); 
		existing.setDescription(dto.getDescription()); 
		existing.setStatus(dto.getStatus()); 
		
		if (dto.getUserId() != null) { 
			userRepository.findById(dto.getUserId()).ifPresent(existing::setUser); 
			} 
		Set<Tag> tags = new HashSet<>(); 
		if (dto.getTagIds() != null) { 
			for (Long tagId : dto.getTagIds()) { 
				tagRepository.findById(tagId).ifPresent(tags::add); 
				} } 
		existing.setTags(tags); 

		existing.setUpdatedAt(LocalDateTime.now()); 
		Task saved = taskRepository.save(existing);
		
		return saved;
	}

	@Override
	public Object getTaskById(Long id) {
		Task task = taskRepository.findById(id) .orElseThrow(() -> new RuntimeException("Task not found with id: "+ id));
		return task;
	}
	@Override
	public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }
	@Override
    public Page<Task> getTasksByStatus(Status status, Pageable pageable) {
        return taskRepository.findByStatus(status, pageable);
    }
	@Override
	public void deleteTask(Long id) {
		// TODO Auto-generated method stub
		Task existing = taskRepository.findById(id) .orElseThrow(() -> new RuntimeException("Task not found with id: " + id)); 
		taskRepository.delete(existing);
		
	}

}
